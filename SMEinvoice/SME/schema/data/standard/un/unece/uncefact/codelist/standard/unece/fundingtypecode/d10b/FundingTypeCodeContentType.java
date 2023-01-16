//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.codelist.standard.unece.fundingtypecode.d10b;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>FundingTypeCodeContentTypeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * <pre>
 * &lt;simpleType name="FundingTypeCodeContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="INC"/>
 *     &lt;enumeration value="MYR"/>
 *     &lt;enumeration value="SYR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FundingTypeCodeContentType", namespace = "urn:un:unece:uncefact:codelist:standard:UNECE:FundingTypeCode:D10B")
@XmlEnum
public enum FundingTypeCodeContentType {


    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69659="urn:un:unece:uncefact:codelist:standard:UNECE:FundingTypeCode:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Incremental&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69659="urn:un:unece:uncefact:codelist:standard:UNECE:FundingTypeCode:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Funded in arbitrary increments&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    INC,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69659="urn:un:unece:uncefact:codelist:standard:UNECE:FundingTypeCode:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Multi-year&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69659="urn:un:unece:uncefact:codelist:standard:UNECE:FundingTypeCode:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Funded over multiple years.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    MYR,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69659="urn:un:unece:uncefact:codelist:standard:UNECE:FundingTypeCode:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Single year&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69659="urn:un:unece:uncefact:codelist:standard:UNECE:FundingTypeCode:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Funded for a single year.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    SYR;

    public String value() {
        return name();
    }

    public static FundingTypeCodeContentType fromValue(String v) {
        return valueOf(v);
    }

}