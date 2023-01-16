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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.CodeType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.TextType;


/**
 * <p>CITradeSettlementPaymentMeansType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CITradeSettlementPaymentMeansType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TypeCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType" minOccurs="0"/>
 *         &lt;element name="Information" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *         &lt;element name="aidAmount" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}AmountType" minOccurs="0"/>
 *         &lt;element name="PayeePartyCICreditorFinancialAccount" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CICreditorFinancialAccountType" minOccurs="0"/>
 *         &lt;element name="PayeeSpecifiedCICreditorFinancialInstitution" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CICreditorFinancialInstitutionType" minOccurs="0"/>
 *         &lt;element name="ApplicableTradeSettlementFinancialCard" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}TradeSettlementFinancialCardType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CITradeSettlementPaymentMeansType", propOrder = {
    "typeCode",
    "information",
    "aidAmount",
    "payeePartyCICreditorFinancialAccount",
    "payeeSpecifiedCICreditorFinancialInstitution",
    "applicableTradeSettlementFinancialCard"
})
public class CITradeSettlementPaymentMeansType {

    @XmlElement(name = "TypeCode")
    protected CodeType typeCode;
    @XmlElement(name = "Information")
    protected TextType information;
    protected AmountType aidAmount;
    @XmlElement(name = "PayeePartyCICreditorFinancialAccount")
    protected CICreditorFinancialAccountType payeePartyCICreditorFinancialAccount;
    @XmlElement(name = "PayeeSpecifiedCICreditorFinancialInstitution")
    protected CICreditorFinancialInstitutionType payeeSpecifiedCICreditorFinancialInstitution;
    @XmlElement(name = "ApplicableTradeSettlementFinancialCard")
    protected TradeSettlementFinancialCardType applicableTradeSettlementFinancialCard;

    /**
     * typeCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getTypeCode() {
        return typeCode;
    }

    /**
     * typeCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setTypeCode(CodeType value) {
        this.typeCode = value;
    }

    /**
     * informationプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getInformation() {
        return information;
    }

    /**
     * informationプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setInformation(TextType value) {
        this.information = value;
    }

    /**
     * aidAmountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getAidAmount() {
        return aidAmount;
    }

    /**
     * aidAmountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setAidAmount(AmountType value) {
        this.aidAmount = value;
    }

    /**
     * payeePartyCICreditorFinancialAccountプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CICreditorFinancialAccountType }
     *     
     */
    public CICreditorFinancialAccountType getPayeePartyCICreditorFinancialAccount() {
        return payeePartyCICreditorFinancialAccount;
    }

    /**
     * payeePartyCICreditorFinancialAccountプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CICreditorFinancialAccountType }
     *     
     */
    public void setPayeePartyCICreditorFinancialAccount(CICreditorFinancialAccountType value) {
        this.payeePartyCICreditorFinancialAccount = value;
    }

    /**
     * payeeSpecifiedCICreditorFinancialInstitutionプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CICreditorFinancialInstitutionType }
     *     
     */
    public CICreditorFinancialInstitutionType getPayeeSpecifiedCICreditorFinancialInstitution() {
        return payeeSpecifiedCICreditorFinancialInstitution;
    }

    /**
     * payeeSpecifiedCICreditorFinancialInstitutionプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CICreditorFinancialInstitutionType }
     *     
     */
    public void setPayeeSpecifiedCICreditorFinancialInstitution(CICreditorFinancialInstitutionType value) {
        this.payeeSpecifiedCICreditorFinancialInstitution = value;
    }

    /**
     * applicableTradeSettlementFinancialCardプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TradeSettlementFinancialCardType }
     *     
     */
    public TradeSettlementFinancialCardType getApplicableTradeSettlementFinancialCard() {
        return applicableTradeSettlementFinancialCard;
    }

    /**
     * applicableTradeSettlementFinancialCardプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TradeSettlementFinancialCardType }
     *     
     */
    public void setApplicableTradeSettlementFinancialCard(TradeSettlementFinancialCardType value) {
        this.applicableTradeSettlementFinancialCard = value;
    }

}
