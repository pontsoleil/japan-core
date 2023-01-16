//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._31;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.AmountType;


/**
 * <p>CIILTradeSettlementMonetarySummationType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIILTradeSettlementMonetarySummationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TaxTotalAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType" minOccurs="0"/>
 *         &lt;element name="NetLineTotalAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType"/>
 *         &lt;element name="NetIncludingTaxesLineTotalAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType" minOccurs="0"/>
 *         &lt;element name="GrandTotalAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIILTradeSettlementMonetarySummationType", propOrder = {
    "taxTotalAmount",
    "netLineTotalAmount",
    "netIncludingTaxesLineTotalAmount",
    "grandTotalAmount"
})
public class CIILTradeSettlementMonetarySummationType {

    @XmlElement(name = "TaxTotalAmount")
    protected AmountType taxTotalAmount;
    @XmlElement(name = "NetLineTotalAmount", required = true)
    protected AmountType netLineTotalAmount;
    @XmlElement(name = "NetIncludingTaxesLineTotalAmount")
    protected AmountType netIncludingTaxesLineTotalAmount;
    @XmlElement(name = "GrandTotalAmount")
    protected AmountType grandTotalAmount;

    /**
     * taxTotalAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getTaxTotalAmount() {
        return taxTotalAmount;
    }

    /**
     * taxTotalAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setTaxTotalAmount(AmountType value) {
        this.taxTotalAmount = value;
    }

    /**
     * netLineTotalAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getNetLineTotalAmount() {
        return netLineTotalAmount;
    }

    /**
     * netLineTotalAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setNetLineTotalAmount(AmountType value) {
        this.netLineTotalAmount = value;
    }

    /**
     * netIncludingTaxesLineTotalAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getNetIncludingTaxesLineTotalAmount() {
        return netIncludingTaxesLineTotalAmount;
    }

    /**
     * netIncludingTaxesLineTotalAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setNetIncludingTaxesLineTotalAmount(AmountType value) {
        this.netIncludingTaxesLineTotalAmount = value;
    }

    /**
     * grandTotalAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getGrandTotalAmount() {
        return grandTotalAmount;
    }

    /**
     * grandTotalAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setGrandTotalAmount(AmountType value) {
        this.grandTotalAmount = value;
    }

}
