//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.codelist.standard.unece.contractortype.d10b;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ContractorTypeContentTypeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * <pre>
 * &lt;simpleType name="ContractorTypeContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="DRS"/>
 *     &lt;enumeration value="PAC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ContractorTypeContentType", namespace = "urn:un:unece:uncefact:codelist:standard:UNECE:ContractorType:D10B")
@XmlEnum
public enum ContractorTypeContentType {


    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6ContractorType="urn:un:unece:uncefact:codelist:standard:UNECE:ContractorType:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Direct Reporting Subcontractor&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6ContractorType="urn:un:unece:uncefact:codelist:standard:UNECE:ContractorType:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Subcontractor that reports directly to the customer or client.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    DRS,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6ContractorType="urn:un:unece:uncefact:codelist:standard:UNECE:ContractorType:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Primary or Associate Contractor&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6ContractorType="urn:un:unece:uncefact:codelist:standard:UNECE:ContractorType:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Primary contractor or associate contractor&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    PAC;

    public String value() {
        return name();
    }

    public static ContractorTypeContentType fromValue(String v) {
        return valueOf(v);
    }

}
