//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.codelist.standard.unece.measurementunitcommoncodelinear._4;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MeasurementUnitCommonCodeLinearContentTypeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * <pre>
 * &lt;simpleType name="MeasurementUnitCommonCodeLinearContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="CMT"/>
 *     &lt;enumeration value="FOT"/>
 *     &lt;enumeration value="INH"/>
 *     &lt;enumeration value="MTR"/>
 *     &lt;minLength value="1"/>
 *     &lt;maxLength value="3"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MeasurementUnitCommonCodeLinearContentType", namespace = "urn:un:unece:uncefact:codelist:standard:UNECE:MeasurementUnitCommonCodeLinear:4")
@XmlEnum
public enum MeasurementUnitCommonCodeLinearContentType {


    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6Recommendation20Linear="urn:un:unece:uncefact:codelist:standard:UNECE:MeasurementUnitCommonCodeLinear:4" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;centimetre&lt;/ccts:Name&gt;
     * </pre>
     * 
     *           
     * 
     */
    CMT,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6Recommendation20Linear="urn:un:unece:uncefact:codelist:standard:UNECE:MeasurementUnitCommonCodeLinear:4" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;foot&lt;/ccts:Name&gt;
     * </pre>
     * 
     *           
     * 
     */
    FOT,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6Recommendation20Linear="urn:un:unece:uncefact:codelist:standard:UNECE:MeasurementUnitCommonCodeLinear:4" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;inch&lt;/ccts:Name&gt;
     * </pre>
     * 
     *           
     * 
     */
    INH,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6Recommendation20Linear="urn:un:unece:uncefact:codelist:standard:UNECE:MeasurementUnitCommonCodeLinear:4" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;metre&lt;/ccts:Name&gt;
     * </pre>
     * 
     *           
     * 
     */
    MTR;

    public String value() {
        return name();
    }

    public static MeasurementUnitCommonCodeLinearContentType fromValue(String v) {
        return valueOf(v);
    }

}
