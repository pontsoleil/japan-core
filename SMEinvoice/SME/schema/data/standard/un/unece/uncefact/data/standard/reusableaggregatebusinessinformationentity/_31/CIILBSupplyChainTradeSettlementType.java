//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._31;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.CodeType;


/**
 * <p>CIILBSupplyChainTradeSettlementType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIILBSupplyChainTradeSettlementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DirectionCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType"/>
 *         &lt;element name="ApplicableCITradeTax" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradeTaxType"/>
 *         &lt;element name="InvoiceReferencedCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SpecifiedCITradeAllowanceCharge" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradeAllowanceChargeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SpecifiedCIFinancialAdjustment" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIFinancialAdjustmentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BillingCISpecifiedPeriod" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CISpecifiedPeriodType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIILBSupplyChainTradeSettlementType", propOrder = {
    "directionCode",
    "applicableCITradeTax",
    "invoiceReferencedCIReferencedDocument",
    "specifiedCITradeAllowanceCharge",
    "specifiedCIFinancialAdjustment",
    "billingCISpecifiedPeriod"
})
public class CIILBSupplyChainTradeSettlementType {

    @XmlElement(name = "DirectionCode", required = true)
    protected CodeType directionCode;
    @XmlElement(name = "ApplicableCITradeTax", required = true)
    protected CITradeTaxType applicableCITradeTax;
    @XmlElement(name = "InvoiceReferencedCIReferencedDocument")
    protected List<CIReferencedDocumentType> invoiceReferencedCIReferencedDocument;
    @XmlElement(name = "SpecifiedCITradeAllowanceCharge")
    protected List<CITradeAllowanceChargeType> specifiedCITradeAllowanceCharge;
    @XmlElement(name = "SpecifiedCIFinancialAdjustment")
    protected List<CIFinancialAdjustmentType> specifiedCIFinancialAdjustment;
    @XmlElement(name = "BillingCISpecifiedPeriod", required = true)
    protected CISpecifiedPeriodType billingCISpecifiedPeriod;

    /**
     * directionCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getDirectionCode() {
        return directionCode;
    }

    /**
     * directionCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setDirectionCode(CodeType value) {
        this.directionCode = value;
    }

    /**
     * applicableCITradeTaxプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradeTaxType }
     *     
     */
    public CITradeTaxType getApplicableCITradeTax() {
        return applicableCITradeTax;
    }

    /**
     * applicableCITradeTaxプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradeTaxType }
     *     
     */
    public void setApplicableCITradeTax(CITradeTaxType value) {
        this.applicableCITradeTax = value;
    }

    /**
     * Gets the value of the invoiceReferencedCIReferencedDocument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceReferencedCIReferencedDocument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceReferencedCIReferencedDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIReferencedDocumentType }
     * 
     * 
     */
    public List<CIReferencedDocumentType> getInvoiceReferencedCIReferencedDocument() {
        if (invoiceReferencedCIReferencedDocument == null) {
            invoiceReferencedCIReferencedDocument = new ArrayList<CIReferencedDocumentType>();
        }
        return this.invoiceReferencedCIReferencedDocument;
    }

    /**
     * Gets the value of the specifiedCITradeAllowanceCharge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specifiedCITradeAllowanceCharge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecifiedCITradeAllowanceCharge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CITradeAllowanceChargeType }
     * 
     * 
     */
    public List<CITradeAllowanceChargeType> getSpecifiedCITradeAllowanceCharge() {
        if (specifiedCITradeAllowanceCharge == null) {
            specifiedCITradeAllowanceCharge = new ArrayList<CITradeAllowanceChargeType>();
        }
        return this.specifiedCITradeAllowanceCharge;
    }

    /**
     * Gets the value of the specifiedCIFinancialAdjustment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specifiedCIFinancialAdjustment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecifiedCIFinancialAdjustment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIFinancialAdjustmentType }
     * 
     * 
     */
    public List<CIFinancialAdjustmentType> getSpecifiedCIFinancialAdjustment() {
        if (specifiedCIFinancialAdjustment == null) {
            specifiedCIFinancialAdjustment = new ArrayList<CIFinancialAdjustmentType>();
        }
        return this.specifiedCIFinancialAdjustment;
    }

    /**
     * billingCISpecifiedPeriodプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CISpecifiedPeriodType }
     *     
     */
    public CISpecifiedPeriodType getBillingCISpecifiedPeriod() {
        return billingCISpecifiedPeriod;
    }

    /**
     * billingCISpecifiedPeriodプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CISpecifiedPeriodType }
     *     
     */
    public void setBillingCISpecifiedPeriod(CISpecifiedPeriodType value) {
        this.billingCISpecifiedPeriod = value;
    }

}
