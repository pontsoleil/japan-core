//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.data.standard.smeinvoice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the un.unece.uncefact.data.standard.smeinvoice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SMEinvoice_QNAME = new QName("urn:un:unece:uncefact:data:standard:SMEinvoice", "SMEinvoice");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: un.unece.uncefact.data.standard.smeinvoice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SMEinvoiceType }
     * 
     */
    public SMEinvoiceType createSMEinvoiceType() {
        return new SMEinvoiceType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SMEinvoiceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:un:unece:uncefact:data:standard:SMEinvoice", name = "SMEinvoice")
    public JAXBElement<SMEinvoiceType> createSMEinvoice(SMEinvoiceType value) {
        return new JAXBElement<SMEinvoiceType>(_SMEinvoice_QNAME, SMEinvoiceType.class, null, value);
    }

}
