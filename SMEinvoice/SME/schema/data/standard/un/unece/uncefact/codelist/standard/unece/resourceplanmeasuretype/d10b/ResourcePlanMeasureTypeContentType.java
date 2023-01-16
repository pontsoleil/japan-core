//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.codelist.standard.unece.resourceplanmeasuretype.d10b;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ResourcePlanMeasureTypeContentTypeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * <pre>
 * &lt;simpleType name="ResourcePlanMeasureTypeContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="DC"/>
 *     &lt;enumeration value="HR"/>
 *     &lt;enumeration value="UN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ResourcePlanMeasureTypeContentType", namespace = "urn:un:unece:uncefact:codelist:standard:UNECE:ResourcePlanMeasureType:D10B")
@XmlEnum
public enum ResourcePlanMeasureTypeContentType {


    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69671="urn:un:unece:uncefact:codelist:standard:UNECE:ResourcePlanMeasureType:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Direct costs&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69671="urn:un:unece:uncefact:codelist:standard:UNECE:ResourcePlanMeasureType:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Resources are measured by the direct costs.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    DC,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69671="urn:un:unece:uncefact:codelist:standard:UNECE:ResourcePlanMeasureType:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Hours&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69671="urn:un:unece:uncefact:codelist:standard:UNECE:ResourcePlanMeasureType:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Resources are measured by the labour hours.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    HR,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69671="urn:un:unece:uncefact:codelist:standard:UNECE:ResourcePlanMeasureType:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Other units&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm69671="urn:un:unece:uncefact:codelist:standard:UNECE:ResourcePlanMeasureType:D10B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Resources are measured by other units.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    UN;

    public String value() {
        return name();
    }

    public static ResourcePlanMeasureTypeContentType fromValue(String v) {
        return valueOf(v);
    }

}
