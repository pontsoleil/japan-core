//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.codelist.standard.edificas_eu.softwareusertype.d11a;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SoftwareUserTypeContentTypeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * <pre>
 * &lt;simpleType name="SoftwareUserTypeContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="R"/>
 *     &lt;enumeration value="S"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SoftwareUserTypeContentType", namespace = "urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:SoftwareUserType:D11A")
@XmlEnum
public enum SoftwareUserTypeContentType {


    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210SoftwareUserTypeE019="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:SoftwareUserType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Intermediate&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210SoftwareUserTypeE019="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:SoftwareUserType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;The code indicates an intermediate.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    I,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210SoftwareUserTypeE019="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:SoftwareUserType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Recipient&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210SoftwareUserTypeE019="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:SoftwareUserType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;The code indicates a recipient.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    R,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210SoftwareUserTypeE019="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:SoftwareUserType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Sender&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210SoftwareUserTypeE019="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:SoftwareUserType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;The code indicates a sender.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    S;

    public String value() {
        return name();
    }

    public static SoftwareUserTypeContentType fromValue(String v) {
        return valueOf(v);
    }

}