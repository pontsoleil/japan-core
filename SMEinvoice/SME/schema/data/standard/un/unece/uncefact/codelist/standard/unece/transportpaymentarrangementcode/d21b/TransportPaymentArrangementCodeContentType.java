//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.codelist.standard.unece.transportpaymentarrangementcode.d21b;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TransportPaymentArrangementCodeContentTypeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * <pre>
 * &lt;simpleType name="TransportPaymentArrangementCodeContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="A"/>
 *     &lt;enumeration value="B"/>
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="P"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransportPaymentArrangementCodeContentType", namespace = "urn:un:unece:uncefact:codelist:standard:UNECE:TransportPaymentArrangementCode:D21B")
@XmlEnum
public enum TransportPaymentArrangementCodeContentType {


    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm64237TransportPaymentArrangementCode="urn:un:unece:uncefact:codelist:standard:UNECE:TransportPaymentArrangementCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Payable elsewhere&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm64237TransportPaymentArrangementCode="urn:un:unece:uncefact:codelist:standard:UNECE:TransportPaymentArrangementCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Responsibility for payment of transport charges unknown at time of departure.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    A,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm64237TransportPaymentArrangementCode="urn:un:unece:uncefact:codelist:standard:UNECE:TransportPaymentArrangementCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Third party to pay&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm64237TransportPaymentArrangementCode="urn:un:unece:uncefact:codelist:standard:UNECE:TransportPaymentArrangementCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;A third party to pay the freight bill is known at the time of shipment.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    B,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm64237TransportPaymentArrangementCode="urn:un:unece:uncefact:codelist:standard:UNECE:TransportPaymentArrangementCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Collect&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm64237TransportPaymentArrangementCode="urn:un:unece:uncefact:codelist:standard:UNECE:TransportPaymentArrangementCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Charges are (to be) collected from the consignee at the destination.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    C,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm64237TransportPaymentArrangementCode="urn:un:unece:uncefact:codelist:standard:UNECE:TransportPaymentArrangementCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Prepaid&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm64237TransportPaymentArrangementCode="urn:un:unece:uncefact:codelist:standard:UNECE:TransportPaymentArrangementCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Charges are (to be) prepaid before the transport actually leaves.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    P;

    public String value() {
        return name();
    }

    public static TransportPaymentArrangementCodeContentType fromValue(String v) {
        return valueOf(v);
    }

}
