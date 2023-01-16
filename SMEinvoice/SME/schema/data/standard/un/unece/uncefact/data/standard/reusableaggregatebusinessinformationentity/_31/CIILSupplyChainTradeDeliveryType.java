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
 * <p>CIILSupplyChainTradeDeliveryType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIILSupplyChainTradeDeliveryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipToCITradeParty" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradePartyType" minOccurs="0"/>
 *         &lt;element name="ActualDeliveryCISupplyChainEvent" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CISupplyChainEventType" minOccurs="0"/>
 *         &lt;element name="DeliveryNoteReferencedCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIILSupplyChainTradeDeliveryType", propOrder = {
    "shipToCITradeParty",
    "actualDeliveryCISupplyChainEvent",
    "deliveryNoteReferencedCIReferencedDocument"
})
public class CIILSupplyChainTradeDeliveryType {

    @XmlElement(name = "ShipToCITradeParty")
    protected CITradePartyType shipToCITradeParty;
    @XmlElement(name = "ActualDeliveryCISupplyChainEvent")
    protected CISupplyChainEventType actualDeliveryCISupplyChainEvent;
    @XmlElement(name = "DeliveryNoteReferencedCIReferencedDocument")
    protected List<CIReferencedDocumentType> deliveryNoteReferencedCIReferencedDocument;

    /**
     * shipToCITradePartyプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradePartyType }
     *     
     */
    public CITradePartyType getShipToCITradeParty() {
        return shipToCITradeParty;
    }

    /**
     * shipToCITradePartyプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradePartyType }
     *     
     */
    public void setShipToCITradeParty(CITradePartyType value) {
        this.shipToCITradeParty = value;
    }

    /**
     * actualDeliveryCISupplyChainEventプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CISupplyChainEventType }
     *     
     */
    public CISupplyChainEventType getActualDeliveryCISupplyChainEvent() {
        return actualDeliveryCISupplyChainEvent;
    }

    /**
     * actualDeliveryCISupplyChainEventプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CISupplyChainEventType }
     *     
     */
    public void setActualDeliveryCISupplyChainEvent(CISupplyChainEventType value) {
        this.actualDeliveryCISupplyChainEvent = value;
    }

    /**
     * Gets the value of the deliveryNoteReferencedCIReferencedDocument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryNoteReferencedCIReferencedDocument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryNoteReferencedCIReferencedDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIReferencedDocumentType }
     * 
     * 
     */
    public List<CIReferencedDocumentType> getDeliveryNoteReferencedCIReferencedDocument() {
        if (deliveryNoteReferencedCIReferencedDocument == null) {
            deliveryNoteReferencedCIReferencedDocument = new ArrayList<CIReferencedDocumentType>();
        }
        return this.deliveryNoteReferencedCIReferencedDocument;
    }

}
