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
 * <p>CIIHSupplyChainTradeSettlementType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIIHSupplyChainTradeSettlementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TaxCurrencyCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType"/>
 *         &lt;element name="InvoiceCurrencyCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType"/>
 *         &lt;element name="PaymentCurrencyCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType" minOccurs="0"/>
 *         &lt;element name="InvoicerCITradeParty" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradePartyType" minOccurs="0"/>
 *         &lt;element name="InvoiceeCITradeParty" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradePartyType" minOccurs="0"/>
 *         &lt;element name="PayeeCITradeParty" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradePartyType" minOccurs="0"/>
 *         &lt;element name="PayerCITradeParty" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradePartyType" minOccurs="0"/>
 *         &lt;element name="InvoiceApplicableCITradeCurrencyExchange" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradeCurrencyExchangeType" minOccurs="0"/>
 *         &lt;element name="PaymentApplicableCITradeCurrencyExchange" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradeCurrencyExchangeType" minOccurs="0"/>
 *         &lt;element name="SpecifiedCITradeSettlementPaymentMeans" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradeSettlementPaymentMeansType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SpecifiedCITradeAllowanceCharge" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradeAllowanceChargeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ApplicableCITradeTax" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradeTaxType"/>
 *         &lt;element name="BillingCISpecifiedPeriod" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CISpecifiedPeriodType"/>
 *         &lt;element name="SpecifiedCITradePaymentTerms" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradePaymentTermsType" minOccurs="0"/>
 *         &lt;element name="SpecifiedCIIHTradeSettlementMonetarySummation" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIIHTradeSettlementMonetarySummationType" minOccurs="0"/>
 *         &lt;element name="SpecifiedCIFinancialAdjustment" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIFinancialAdjustmentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OutstandingCIIHTradeSettlementMonetarySummation" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIIHTradeSettlementMonetarySummationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIIHSupplyChainTradeSettlementType", propOrder = {
    "taxCurrencyCode",
    "invoiceCurrencyCode",
    "paymentCurrencyCode",
    "invoicerCITradeParty",
    "invoiceeCITradeParty",
    "payeeCITradeParty",
    "payerCITradeParty",
    "invoiceApplicableCITradeCurrencyExchange",
    "paymentApplicableCITradeCurrencyExchange",
    "specifiedCITradeSettlementPaymentMeans",
    "specifiedCITradeAllowanceCharge",
    "applicableCITradeTax",
    "billingCISpecifiedPeriod",
    "specifiedCITradePaymentTerms",
    "specifiedCIIHTradeSettlementMonetarySummation",
    "specifiedCIFinancialAdjustment",
    "outstandingCIIHTradeSettlementMonetarySummation"
})
public class CIIHSupplyChainTradeSettlementType {

    @XmlElement(name = "TaxCurrencyCode", required = true)
    protected CodeType taxCurrencyCode;
    @XmlElement(name = "InvoiceCurrencyCode", required = true)
    protected CodeType invoiceCurrencyCode;
    @XmlElement(name = "PaymentCurrencyCode")
    protected CodeType paymentCurrencyCode;
    @XmlElement(name = "InvoicerCITradeParty")
    protected CITradePartyType invoicerCITradeParty;
    @XmlElement(name = "InvoiceeCITradeParty")
    protected CITradePartyType invoiceeCITradeParty;
    @XmlElement(name = "PayeeCITradeParty")
    protected CITradePartyType payeeCITradeParty;
    @XmlElement(name = "PayerCITradeParty")
    protected CITradePartyType payerCITradeParty;
    @XmlElement(name = "InvoiceApplicableCITradeCurrencyExchange")
    protected CITradeCurrencyExchangeType invoiceApplicableCITradeCurrencyExchange;
    @XmlElement(name = "PaymentApplicableCITradeCurrencyExchange")
    protected CITradeCurrencyExchangeType paymentApplicableCITradeCurrencyExchange;
    @XmlElement(name = "SpecifiedCITradeSettlementPaymentMeans")
    protected List<CITradeSettlementPaymentMeansType> specifiedCITradeSettlementPaymentMeans;
    @XmlElement(name = "SpecifiedCITradeAllowanceCharge")
    protected List<CITradeAllowanceChargeType> specifiedCITradeAllowanceCharge;
    @XmlElement(name = "ApplicableCITradeTax", required = true)
    protected CITradeTaxType applicableCITradeTax;
    @XmlElement(name = "BillingCISpecifiedPeriod", required = true)
    protected CISpecifiedPeriodType billingCISpecifiedPeriod;
    @XmlElement(name = "SpecifiedCITradePaymentTerms")
    protected CITradePaymentTermsType specifiedCITradePaymentTerms;
    @XmlElement(name = "SpecifiedCIIHTradeSettlementMonetarySummation")
    protected CIIHTradeSettlementMonetarySummationType specifiedCIIHTradeSettlementMonetarySummation;
    @XmlElement(name = "SpecifiedCIFinancialAdjustment")
    protected List<CIFinancialAdjustmentType> specifiedCIFinancialAdjustment;
    @XmlElement(name = "OutstandingCIIHTradeSettlementMonetarySummation")
    protected CIIHTradeSettlementMonetarySummationType outstandingCIIHTradeSettlementMonetarySummation;

    /**
     * taxCurrencyCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getTaxCurrencyCode() {
        return taxCurrencyCode;
    }

    /**
     * taxCurrencyCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setTaxCurrencyCode(CodeType value) {
        this.taxCurrencyCode = value;
    }

    /**
     * invoiceCurrencyCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getInvoiceCurrencyCode() {
        return invoiceCurrencyCode;
    }

    /**
     * invoiceCurrencyCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setInvoiceCurrencyCode(CodeType value) {
        this.invoiceCurrencyCode = value;
    }

    /**
     * paymentCurrencyCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getPaymentCurrencyCode() {
        return paymentCurrencyCode;
    }

    /**
     * paymentCurrencyCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setPaymentCurrencyCode(CodeType value) {
        this.paymentCurrencyCode = value;
    }

    /**
     * invoicerCITradePartyプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradePartyType }
     *     
     */
    public CITradePartyType getInvoicerCITradeParty() {
        return invoicerCITradeParty;
    }

    /**
     * invoicerCITradePartyプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradePartyType }
     *     
     */
    public void setInvoicerCITradeParty(CITradePartyType value) {
        this.invoicerCITradeParty = value;
    }

    /**
     * invoiceeCITradePartyプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradePartyType }
     *     
     */
    public CITradePartyType getInvoiceeCITradeParty() {
        return invoiceeCITradeParty;
    }

    /**
     * invoiceeCITradePartyプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradePartyType }
     *     
     */
    public void setInvoiceeCITradeParty(CITradePartyType value) {
        this.invoiceeCITradeParty = value;
    }

    /**
     * payeeCITradePartyプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradePartyType }
     *     
     */
    public CITradePartyType getPayeeCITradeParty() {
        return payeeCITradeParty;
    }

    /**
     * payeeCITradePartyプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradePartyType }
     *     
     */
    public void setPayeeCITradeParty(CITradePartyType value) {
        this.payeeCITradeParty = value;
    }

    /**
     * payerCITradePartyプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradePartyType }
     *     
     */
    public CITradePartyType getPayerCITradeParty() {
        return payerCITradeParty;
    }

    /**
     * payerCITradePartyプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradePartyType }
     *     
     */
    public void setPayerCITradeParty(CITradePartyType value) {
        this.payerCITradeParty = value;
    }

    /**
     * invoiceApplicableCITradeCurrencyExchangeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradeCurrencyExchangeType }
     *     
     */
    public CITradeCurrencyExchangeType getInvoiceApplicableCITradeCurrencyExchange() {
        return invoiceApplicableCITradeCurrencyExchange;
    }

    /**
     * invoiceApplicableCITradeCurrencyExchangeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradeCurrencyExchangeType }
     *     
     */
    public void setInvoiceApplicableCITradeCurrencyExchange(CITradeCurrencyExchangeType value) {
        this.invoiceApplicableCITradeCurrencyExchange = value;
    }

    /**
     * paymentApplicableCITradeCurrencyExchangeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradeCurrencyExchangeType }
     *     
     */
    public CITradeCurrencyExchangeType getPaymentApplicableCITradeCurrencyExchange() {
        return paymentApplicableCITradeCurrencyExchange;
    }

    /**
     * paymentApplicableCITradeCurrencyExchangeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradeCurrencyExchangeType }
     *     
     */
    public void setPaymentApplicableCITradeCurrencyExchange(CITradeCurrencyExchangeType value) {
        this.paymentApplicableCITradeCurrencyExchange = value;
    }

    /**
     * Gets the value of the specifiedCITradeSettlementPaymentMeans property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specifiedCITradeSettlementPaymentMeans property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecifiedCITradeSettlementPaymentMeans().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CITradeSettlementPaymentMeansType }
     * 
     * 
     */
    public List<CITradeSettlementPaymentMeansType> getSpecifiedCITradeSettlementPaymentMeans() {
        if (specifiedCITradeSettlementPaymentMeans == null) {
            specifiedCITradeSettlementPaymentMeans = new ArrayList<CITradeSettlementPaymentMeansType>();
        }
        return this.specifiedCITradeSettlementPaymentMeans;
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

    /**
     * specifiedCITradePaymentTermsプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradePaymentTermsType }
     *     
     */
    public CITradePaymentTermsType getSpecifiedCITradePaymentTerms() {
        return specifiedCITradePaymentTerms;
    }

    /**
     * specifiedCITradePaymentTermsプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradePaymentTermsType }
     *     
     */
    public void setSpecifiedCITradePaymentTerms(CITradePaymentTermsType value) {
        this.specifiedCITradePaymentTerms = value;
    }

    /**
     * specifiedCIIHTradeSettlementMonetarySummationプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIIHTradeSettlementMonetarySummationType }
     *     
     */
    public CIIHTradeSettlementMonetarySummationType getSpecifiedCIIHTradeSettlementMonetarySummation() {
        return specifiedCIIHTradeSettlementMonetarySummation;
    }

    /**
     * specifiedCIIHTradeSettlementMonetarySummationプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIIHTradeSettlementMonetarySummationType }
     *     
     */
    public void setSpecifiedCIIHTradeSettlementMonetarySummation(CIIHTradeSettlementMonetarySummationType value) {
        this.specifiedCIIHTradeSettlementMonetarySummation = value;
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
     * outstandingCIIHTradeSettlementMonetarySummationプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIIHTradeSettlementMonetarySummationType }
     *     
     */
    public CIIHTradeSettlementMonetarySummationType getOutstandingCIIHTradeSettlementMonetarySummation() {
        return outstandingCIIHTradeSettlementMonetarySummation;
    }

    /**
     * outstandingCIIHTradeSettlementMonetarySummationプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIIHTradeSettlementMonetarySummationType }
     *     
     */
    public void setOutstandingCIIHTradeSettlementMonetarySummation(CIIHTradeSettlementMonetarySummationType value) {
        this.outstandingCIIHTradeSettlementMonetarySummation = value;
    }

}
