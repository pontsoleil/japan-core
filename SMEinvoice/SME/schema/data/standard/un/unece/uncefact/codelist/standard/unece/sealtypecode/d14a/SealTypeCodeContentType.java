//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.codelist.standard.unece.sealtypecode.d14a;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SealTypeCodeContentTypeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * <pre>
 * &lt;simpleType name="SealTypeCodeContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ZZZ"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SealTypeCodeContentType", namespace = "urn:un:unece:uncefact:codelist:standard:UNECE:SealTypeCode:D14A")
@XmlEnum
public enum SealTypeCodeContentType {


    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6SealTypeCode="urn:un:unece:uncefact:codelist:standard:UNECE:SealTypeCode:D14A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Mutually defined&lt;/ccts:Name&gt;
     * </pre>
     * 
     *           
     * 
     */
    ZZZ;

    public String value() {
        return name();
    }

    public static SealTypeCodeContentType fromValue(String v) {
        return valueOf(v);
    }

}
