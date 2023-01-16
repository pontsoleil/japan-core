//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.codelist.standard.edificas_eu.accountingamountqualifiertype.d11a;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AccountingAmountQualifierTypeContentTypeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * <pre>
 * &lt;simpleType name="AccountingAmountQualifierTypeContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="EN"/>
 *     &lt;enumeration value="SB"/>
 *     &lt;enumeration value="SE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccountingAmountQualifierTypeContentType", namespace = "urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:AccountingAmountQualifierType:D11A")
@XmlEnum
public enum AccountingAmountQualifierTypeContentType {


    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210AccountingE603="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:AccountingAmountQualifierType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Entries&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210AccountingE603="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:AccountingAmountQualifierType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;The code indicates the entries of the accounting or fiscal year.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    EN,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210AccountingE603="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:AccountingAmountQualifierType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Open balance&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210AccountingE603="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:AccountingAmountQualifierType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;The code indicates the balance of the open accounting or fiscal year.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    SB,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210AccountingE603="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:AccountingAmountQualifierType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;End balance&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm210AccountingE603="urn:un:unece:uncefact:codelist:standard:EDIFICAS-EU:AccountingAmountQualifierType:D11A" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;The code indicates the balance of the end accounting or fiscal year.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    SE;

    public String value() {
        return name();
    }

    public static AccountingAmountQualifierTypeContentType fromValue(String v) {
        return valueOf(v);
    }

}
