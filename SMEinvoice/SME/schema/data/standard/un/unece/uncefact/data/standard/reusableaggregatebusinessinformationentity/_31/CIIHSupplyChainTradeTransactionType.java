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


/**
 * <p>CIIHSupplyChainTradeTransactionType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIIHSupplyChainTradeTransactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApplicableCIIHSupplyChainTradeAgreement" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIIHSupplyChainTradeAgreementType"/>
 *         &lt;element name="ApplicableCIIHSupplyChainTradeSettlement" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIIHSupplyChainTradeSettlementType"/>
 *         &lt;element name="IncludedCIILSupplyChainTradeLineItem" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIILSupplyChainTradeLineItemType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIIHSupplyChainTradeTransactionType", propOrder = {
    "applicableCIIHSupplyChainTradeAgreement",
    "applicableCIIHSupplyChainTradeSettlement",
    "includedCIILSupplyChainTradeLineItem"
})
public class CIIHSupplyChainTradeTransactionType {

    @XmlElement(name = "ApplicableCIIHSupplyChainTradeAgreement", required = true)
    protected CIIHSupplyChainTradeAgreementType applicableCIIHSupplyChainTradeAgreement;
    @XmlElement(name = "ApplicableCIIHSupplyChainTradeSettlement", required = true)
    protected CIIHSupplyChainTradeSettlementType applicableCIIHSupplyChainTradeSettlement;
    @XmlElement(name = "IncludedCIILSupplyChainTradeLineItem", required = true)
    protected List<CIILSupplyChainTradeLineItemType> includedCIILSupplyChainTradeLineItem;

    /**
     * applicableCIIHSupplyChainTradeAgreementプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIIHSupplyChainTradeAgreementType }
     *     
     */
    public CIIHSupplyChainTradeAgreementType getApplicableCIIHSupplyChainTradeAgreement() {
        return applicableCIIHSupplyChainTradeAgreement;
    }

    /**
     * applicableCIIHSupplyChainTradeAgreementプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIIHSupplyChainTradeAgreementType }
     *     
     */
    public void setApplicableCIIHSupplyChainTradeAgreement(CIIHSupplyChainTradeAgreementType value) {
        this.applicableCIIHSupplyChainTradeAgreement = value;
    }

    /**
     * applicableCIIHSupplyChainTradeSettlementプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIIHSupplyChainTradeSettlementType }
     *     
     */
    public CIIHSupplyChainTradeSettlementType getApplicableCIIHSupplyChainTradeSettlement() {
        return applicableCIIHSupplyChainTradeSettlement;
    }

    /**
     * applicableCIIHSupplyChainTradeSettlementプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIIHSupplyChainTradeSettlementType }
     *     
     */
    public void setApplicableCIIHSupplyChainTradeSettlement(CIIHSupplyChainTradeSettlementType value) {
        this.applicableCIIHSupplyChainTradeSettlement = value;
    }

    /**
     * Gets the value of the includedCIILSupplyChainTradeLineItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includedCIILSupplyChainTradeLineItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludedCIILSupplyChainTradeLineItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIILSupplyChainTradeLineItemType }
     * 
     * 
     */
    public List<CIILSupplyChainTradeLineItemType> getIncludedCIILSupplyChainTradeLineItem() {
        if (includedCIILSupplyChainTradeLineItem == null) {
            includedCIILSupplyChainTradeLineItem = new ArrayList<CIILSupplyChainTradeLineItemType>();
        }
        return this.includedCIILSupplyChainTradeLineItem;
    }

}
