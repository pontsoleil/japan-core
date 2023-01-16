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
 * <p>CIILSupplyChainTradeLineItemType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIILSupplyChainTradeLineItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssociatedCIILDocumentLineDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIILDocumentLineDocumentType"/>
 *         &lt;element name="SpecifiedCIILSupplyChainTradeAgreement" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIILSupplyChainTradeAgreementType" minOccurs="0"/>
 *         &lt;element name="SpecifiedCIILSupplyChainTradeDelivery" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIILSupplyChainTradeDeliveryType" minOccurs="0"/>
 *         &lt;element name="SpecifiedCIILSupplyChainTradeSettlement" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIILSupplyChainTradeSettlementType" minOccurs="0"/>
 *         &lt;element name="SubordinateCIILBSubordinateTradeLineItem" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIILBSubordinateTradeLineItemType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIILSupplyChainTradeLineItemType", propOrder = {
    "associatedCIILDocumentLineDocument",
    "specifiedCIILSupplyChainTradeAgreement",
    "specifiedCIILSupplyChainTradeDelivery",
    "specifiedCIILSupplyChainTradeSettlement",
    "subordinateCIILBSubordinateTradeLineItem"
})
public class CIILSupplyChainTradeLineItemType {

    @XmlElement(name = "AssociatedCIILDocumentLineDocument", required = true)
    protected CIILDocumentLineDocumentType associatedCIILDocumentLineDocument;
    @XmlElement(name = "SpecifiedCIILSupplyChainTradeAgreement")
    protected CIILSupplyChainTradeAgreementType specifiedCIILSupplyChainTradeAgreement;
    @XmlElement(name = "SpecifiedCIILSupplyChainTradeDelivery")
    protected CIILSupplyChainTradeDeliveryType specifiedCIILSupplyChainTradeDelivery;
    @XmlElement(name = "SpecifiedCIILSupplyChainTradeSettlement")
    protected CIILSupplyChainTradeSettlementType specifiedCIILSupplyChainTradeSettlement;
    @XmlElement(name = "SubordinateCIILBSubordinateTradeLineItem", required = true)
    protected List<CIILBSubordinateTradeLineItemType> subordinateCIILBSubordinateTradeLineItem;

    /**
     * associatedCIILDocumentLineDocumentプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIILDocumentLineDocumentType }
     *     
     */
    public CIILDocumentLineDocumentType getAssociatedCIILDocumentLineDocument() {
        return associatedCIILDocumentLineDocument;
    }

    /**
     * associatedCIILDocumentLineDocumentプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIILDocumentLineDocumentType }
     *     
     */
    public void setAssociatedCIILDocumentLineDocument(CIILDocumentLineDocumentType value) {
        this.associatedCIILDocumentLineDocument = value;
    }

    /**
     * specifiedCIILSupplyChainTradeAgreementプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIILSupplyChainTradeAgreementType }
     *     
     */
    public CIILSupplyChainTradeAgreementType getSpecifiedCIILSupplyChainTradeAgreement() {
        return specifiedCIILSupplyChainTradeAgreement;
    }

    /**
     * specifiedCIILSupplyChainTradeAgreementプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIILSupplyChainTradeAgreementType }
     *     
     */
    public void setSpecifiedCIILSupplyChainTradeAgreement(CIILSupplyChainTradeAgreementType value) {
        this.specifiedCIILSupplyChainTradeAgreement = value;
    }

    /**
     * specifiedCIILSupplyChainTradeDeliveryプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIILSupplyChainTradeDeliveryType }
     *     
     */
    public CIILSupplyChainTradeDeliveryType getSpecifiedCIILSupplyChainTradeDelivery() {
        return specifiedCIILSupplyChainTradeDelivery;
    }

    /**
     * specifiedCIILSupplyChainTradeDeliveryプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIILSupplyChainTradeDeliveryType }
     *     
     */
    public void setSpecifiedCIILSupplyChainTradeDelivery(CIILSupplyChainTradeDeliveryType value) {
        this.specifiedCIILSupplyChainTradeDelivery = value;
    }

    /**
     * specifiedCIILSupplyChainTradeSettlementプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIILSupplyChainTradeSettlementType }
     *     
     */
    public CIILSupplyChainTradeSettlementType getSpecifiedCIILSupplyChainTradeSettlement() {
        return specifiedCIILSupplyChainTradeSettlement;
    }

    /**
     * specifiedCIILSupplyChainTradeSettlementプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIILSupplyChainTradeSettlementType }
     *     
     */
    public void setSpecifiedCIILSupplyChainTradeSettlement(CIILSupplyChainTradeSettlementType value) {
        this.specifiedCIILSupplyChainTradeSettlement = value;
    }

    /**
     * Gets the value of the subordinateCIILBSubordinateTradeLineItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subordinateCIILBSubordinateTradeLineItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubordinateCIILBSubordinateTradeLineItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIILBSubordinateTradeLineItemType }
     * 
     * 
     */
    public List<CIILBSubordinateTradeLineItemType> getSubordinateCIILBSubordinateTradeLineItem() {
        if (subordinateCIILBSubordinateTradeLineItem == null) {
            subordinateCIILBSubordinateTradeLineItem = new ArrayList<CIILBSubordinateTradeLineItemType>();
        }
        return this.subordinateCIILBSubordinateTradeLineItem;
    }

}
