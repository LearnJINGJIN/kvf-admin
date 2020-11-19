package com.kalvin.kvf.modules.mts.util;


import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * XmlBinder封装了对JAXB的调用。提供了XML/Java对象的相互转换和验证。
 *
 * 如果需要验证，那么应该提供schema
 *
 * @Auther: jingjin
 * @Date: 2020-10-16 17:53
 * @Modified by:
 * @param <T>
 */
public class XmlBinder<T> {

    public JAXBContext context;

    private Schema schema;

    private Document doc;

    public Document getDocument( File file ) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return doc;
    }

    public T getElementObject( String xpath, Class<T> t ) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath path = xpathFactory.newXPath();
        try {
            Object obj = path.evaluate(xpath, doc, XPathConstants.NODE );
            if( obj == null ) return null;

            return (T) ( getUnmarshaller().unmarshal((Element)obj, t).getValue() );
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 构造一个指定上下文路径的XmlBinder
     *
     * @param contextPath
     */
    public XmlBinder(String contextPath, ClassLoader classLoader) {
        try {
            context = JAXBContext.newInstance(contextPath, classLoader);
        } catch (JAXBException e) {
            //throw Throwables.propagate(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 构造一个指定上下文路径及Schema的XmlBinder
     *
     * @param contextPath
     * @param url
     */
    public XmlBinder(String contextPath, ClassLoader classLoader, URL url) {
        this(contextPath, classLoader);
        schema = createSchema(url);
    }

    /**
     * 根据一个类定义构造一个简单的XmlBinder
     *
     * @param classToBeBound
     */
    public XmlBinder(Class<T> classToBeBound) {
        try {
            context = JAXBContext.newInstance(classToBeBound);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据一个类定义和schema构造一个简单的XmlBinder
     *
     * @param classToBeBound
     * @param url
     */
    public XmlBinder(Class<T> classToBeBound, URL url) {
        this(classToBeBound);
        schema = createSchema(url);
    }

    /**
     * 获得XML编组器
     *
     * @return
     * @throws JAXBException
     */
    protected Marshaller getMarshaller() throws JAXBException {
        return context.createMarshaller();
    }

    /**
     * 获得XML解码器
     *
     * @return
     * @throws JAXBException
     */
    protected Unmarshaller getUnmarshaller() throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();

        if (schema != null)
            unmarshaller.setSchema(schema);
        return unmarshaller;
    }

    /**
     * 从URL获得一个schema对象
     *
     * @param url
     * @return
     */
    private static Schema createSchema(URL url) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            return schemaFactory.newSchema(url);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 编组一个对象到输出流中
     *
     * @param object
     * @param os
     * @throws Exception
     */
    public void marshal(T object, OutputStream os) throws Exception {
        try {
            getMarshaller().marshal(object, os);
        } catch (JAXBException e) {
            throw new Exception("对象转为XML失败", e);
        }
    }

    /**
     * 编组一个对象到字节数组中
     *
     * @param object
     * @return
     * @throws Exception
     */
    public byte[] marshal(T object) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshal(object, baos);
        byte bytes[] = baos.toByteArray();
        baos.close();
        return bytes;
    }

    /**
     * 编组一个对象到字符串
     *
     * @param object
     * @return
     * @throws JAXBException
     * @throws Exception
     */
    public String marshalString(T object) throws JAXBException {
        return marshalString(object, null, false);
    }


    public String marshalString(T object, boolean isFormat ) throws JAXBException {
        return marshalString(object, null, isFormat);
    }

    public String marshalString(T object, String encoding) throws JAXBException {
        return marshalString(object, encoding, false);
    }

    public String marshalString(T object, String encoding, boolean isFormat) throws JAXBException {
        StringWriter writer = new StringWriter();
        Marshaller m = getMarshaller();
        if( encoding != null && encoding.length() > 2 )
            m.setProperty(Marshaller.JAXB_ENCODING, encoding);
        if( isFormat )
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormat);

        m.marshal(object, writer);
        try {
            return writer.toString();
        }
        finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }


    /**
     * 将xml转换为对象
     *
     * @param is
     * @param autoClose
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public T unmarshal(InputStream is, boolean autoClose) throws JAXBException {
        try {
            return (T) getUnmarshaller().unmarshal(is);
        } finally {
            if (autoClose && is != null )
                try {
                    is.close();
                } catch (IOException e) {
                }
        }
    }

    public T unmarshal(File file) throws JAXBException, FileNotFoundException {
        FileInputStream is = new FileInputStream(file);
        return unmarshal(is, true);
    }

    /**
     * 将xml转换为对象
     *
     * @param xml
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public T unmarshal(String xml) throws JAXBException {
        Reader reader = new StringReader(xml);
        try {
            return (T) getUnmarshaller().unmarshal(reader);
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 生成反馈xml文件
     * @param object
     * @param fileNameFullPath
     * @throws Exception
     */
    public void createXmlFile(T object, String fileNameFullPath) throws Exception{

        String requestStr = this.marshalString(object,true);
        /**
         * 生成文件
         */
        File file = null;
        BufferedWriter writer = null;
        try{
            file = new File(fileNameFullPath);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if(file.exists()){
                file.delete();
            }
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(requestStr);
        }catch(Exception e){
            if(file != null && file.exists()){
                file.delete();
            }
            throw e;
        }finally{
            try{
                if(writer != null){
                    writer.flush();
                    writer.close();
                }
            }catch(Exception e){}

        }

    }
}
