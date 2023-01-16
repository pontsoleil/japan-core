//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.codelist.standard.unece.dutyortaxorfeecategorycode.d21b;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DutyorTaxorFeeCategoryCodeContentTypeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <p>
 * <pre>
 * &lt;simpleType name="DutyorTaxorFeeCategoryCodeContentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="A"/>
 *     &lt;enumeration value="AA"/>
 *     &lt;enumeration value="AB"/>
 *     &lt;enumeration value="AC"/>
 *     &lt;enumeration value="AD"/>
 *     &lt;enumeration value="AE"/>
 *     &lt;enumeration value="B"/>
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="D"/>
 *     &lt;enumeration value="E"/>
 *     &lt;enumeration value="F"/>
 *     &lt;enumeration value="G"/>
 *     &lt;enumeration value="H"/>
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="J"/>
 *     &lt;enumeration value="K"/>
 *     &lt;enumeration value="L"/>
 *     &lt;enumeration value="M"/>
 *     &lt;enumeration value="N"/>
 *     &lt;enumeration value="O"/>
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="Z"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DutyorTaxorFeeCategoryCodeContentType", namespace = "urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B")
@XmlEnum
public enum DutyorTaxorFeeCategoryCodeContentType {


    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Mixed tax rate&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code specifying that the rate is based on mixed tax.&lt;/ccts:Description&gt;
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
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Lower rate&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Tax rate is lower than standard rate.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    AA,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Exempt for resale&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;A tax category code indicating the item is tax exempt when the item is bought for future resale.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    AB,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Value Added Tax (VAT) not now due for payment&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;A code to indicate that the Value Added Tax (VAT) amount which is due on the current invoice is to be paid on receipt of a separate VAT payment request.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    AC,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Value Added Tax (VAT) due from a previous invoice&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;A code to indicate that the Value Added Tax (VAT) amount of a previous invoice is to be paid.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    AD,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;VAT Reverse Charge&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code specifying that the standard VAT rate is levied from the invoicee.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    AE,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Transferred (VAT)&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;VAT not to be paid to the issuer of the invoice but directly to relevant tax authority.&lt;/ccts:Description&gt;
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
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Duty paid by supplier&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Duty associated with shipment of goods is paid by the supplier; customer receives goods with duty paid.&lt;/ccts:Description&gt;
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
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Value Added Tax (VAT) margin scheme - travel agents&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Indication that the VAT margin scheme for travel agents is applied.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    D,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Exempt from tax&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code specifying that taxes are not applicable.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    E,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Value Added Tax (VAT) margin scheme - second-hand goods&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Indication that the VAT margin scheme for second-hand goods is applied.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    F,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Free export item, tax not charged&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code specifying that the item is free export and taxes are not charged.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    G,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Higher rate&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code specifying a higher rate of duty or tax or fee.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    H,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Value Added Tax (VAT) margin scheme - works of art Margin scheme — Works of art&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Indication that the VAT margin scheme for works of art is applied.&lt;/ccts:Description&gt;
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
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Value Added Tax (VAT) margin scheme - collector’s items and antiques&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Indication that the VAT margin scheme for collector’s items and antiques is applied.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    J,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;VAT exempt for EEA intra-community supply of goods and services&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;A tax category code indicating the item is VAT exempt due to an intra-community supply in the European Economic Area.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    K,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Canary Islands general indirect tax&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Impuesto General Indirecto Canario (IGIC) is an indirect tax levied on goods and services supplied in the Canary Islands (Spain) by traders and professionals, as well as on import of goods.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    L,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Tax for production, services and importation in Ceuta and Melilla&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Impuesto sobre la Producción, los Servicios y la Importación (IPSI) is an indirect municipal tax, levied on the production, processing and import of all kinds of movable tangible property, the supply of services and the transfer of immovable property located in the cities of Ceuta and Melilla.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    M,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;standard rate additional VAT&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;standard VAT calculated for an additional taxable base when the additional taxable base is not included in the document totals&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    N,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Services outside scope of tax&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code specifying that taxes are not applicable to the services.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    O,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Standard rate&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code specifying the standard rate.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    S,

    /**
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Name xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Zero rated goods&lt;/ccts:Name&gt;
     * </pre>
     * 
     *             
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Description xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2" xmlns:clm65305="urn:un:unece:uncefact:codelist:standard:UNECE:DutyorTaxorFeeCategoryCode:D21B" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code specifying that the goods are at a zero rate.&lt;/ccts:Description&gt;
     * </pre>
     * 
     *           
     * 
     */
    Z;

    public String value() {
        return name();
    }

    public static DutyorTaxorFeeCategoryCodeContentType fromValue(String v) {
        return valueOf(v);
    }

}
