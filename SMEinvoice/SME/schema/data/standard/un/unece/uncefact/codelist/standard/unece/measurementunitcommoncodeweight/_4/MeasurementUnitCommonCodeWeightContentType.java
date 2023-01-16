//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.codelist.standard.unece.measurementunitcommoncodeweight._4;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MeasurementUnitCommonCodeWeightContentTypeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * <pre>
 * &lt;simpleType name="MeasurementUnitCommonCodeWeightContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="KGM"/>
 *     &lt;enumeration value="TNE"/>
 *     &lt;minLength value="1"/>
 *     &lt;maxLength value="3"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MeasurementUnitCommonCodeWeightContentType", namespace = "urn:un:unece:uncefact:codelist:standard:UNECE:MeasurementUnitCommonCodeWeight:4")
@XmlEnum
public enum MeasurementUnitCommonCodeWeightContentType {


    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6Recommendation20Weight="urn:un:unece:uncefact:codelist:standard:UNECE:MeasurementUnitCommonCodeWeight:4" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;kilogram&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6Recommendation20Weight="urn:un:unece:uncefact:codelist:standard:UNECE:MeasurementUnitCommonCodeWeight:4" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;A unit of mass equal to one thousand grams.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    KGM,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6Recommendation20Weight="urn:un:unece:uncefact:codelist:standard:UNECE:MeasurementUnitCommonCodeWeight:4" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;tonne (metric ton)&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm6Recommendation20Weight="urn:un:unece:uncefact:codelist:standard:UNECE:MeasurementUnitCommonCodeWeight:4" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Synonym: metric ton&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    TNE;

    public String value() {
        return name();
    }

    public static MeasurementUnitCommonCodeWeightContentType fromValue(String v) {
        return valueOf(v);
    }

}