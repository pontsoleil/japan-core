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
 * <p>CIIHTradeSettlementMonetarySummationType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIIHTradeSettlementMonetarySummationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChargeTotalAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType" minOccurs="0"/>
 *         &lt;element name="AllowanceTotalAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType" minOccurs="0"/>
 *         &lt;element name="GrandTotalAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType" minOccurs="0"/>
 *         &lt;element name="TotalPrepaidAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType"/>
 *         &lt;element name="DuePayableAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType" minOccurs="0"/>
 *         &lt;element name="ReferenceCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIIHTradeSettlementMonetarySummationType", propOrder = {
    "chargeTotalAmount",
    "allowanceTotalAmount",
    "grandTotalAmount",
    "totalPrepaidAmount",
    "duePayableAmount",
    "referenceCIReferencedDocument"
})
public class CIIHTradeSettlementMonetarySummationType {

    @XmlElement(name = "ChargeTotalAmount")
    protected AmountType chargeTotalAmount;
    @XmlElement(name = "AllowanceTotalAmount")
    protected AmountType allowanceTotalAmount;
    @XmlElement(name = "GrandTotalAmount")
    protected AmountType grandTotalAmount;
    @XmlElement(name = "TotalPrepaidAmount", required = true)
    protected AmountType totalPrepaidAmount;
    @XmlElement(name = "DuePayableAmount")
    protected AmountType duePayableAmount;
    @XmlElement(name = "ReferenceCIReferencedDocument")
    protected CIReferencedDocumentType referenceCIReferencedDocument;

    /**
     * chargeTotalAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getChargeTotalAmount() {
        return chargeTotalAmount;
    }

    /**
     * chargeTotalAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setChargeTotalAmount(AmountType value) {
        this.chargeTotalAmount = value;
    }

    /**
     * allowanceTotalAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getAllowanceTotalAmount() {
        return allowanceTotalAmount;
    }

    /**
     * allowanceTotalAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setAllowanceTotalAmount(AmountType value) {
        this.allowanceTotalAmount = value;
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

    /**
     * totalPrepaidAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getTotalPrepaidAmount() {
        return totalPrepaidAmount;
    }

    /**
     * totalPrepaidAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setTotalPrepaidAmount(AmountType value) {
        this.totalPrepaidAmount = value;
    }

    /**
     * duePayableAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getDuePayableAmount() {
        return duePayableAmount;
    }

    /**
     * duePayableAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setDuePayableAmount(AmountType value) {
        this.duePayableAmount = value;
    }

    /**
     * referenceCIReferencedDocumentプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIReferencedDocumentType }
     *     
     */
    public CIReferencedDocumentType getReferenceCIReferencedDocument() {
        return referenceCIReferencedDocument;
    }

    /**
     * referenceCIReferencedDocumentプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIReferencedDocumentType }
     *     
     */
    public void setReferenceCIReferencedDocument(CIReferencedDocumentType value) {
        this.referenceCIReferencedDocument = value;
    }

}
