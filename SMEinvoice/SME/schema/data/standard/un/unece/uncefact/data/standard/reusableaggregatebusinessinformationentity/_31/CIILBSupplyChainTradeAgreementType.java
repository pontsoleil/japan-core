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
 * <p>CIILBSupplyChainTradeAgreementType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIILBSupplyChainTradeAgreementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SellerOrderReferencedCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" minOccurs="0"/>
 *         &lt;element name="BuyerOrderReferencedCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" minOccurs="0"/>
 *         &lt;element name="AdditionalReferencedCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="NetPriceProductCITradePrice" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CITradePriceType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIILBSupplyChainTradeAgreementType", propOrder = {
    "sellerOrderReferencedCIReferencedDocument",
    "buyerOrderReferencedCIReferencedDocument",
    "additionalReferencedCIReferencedDocument",
    "netPriceProductCITradePrice"
})
public class CIILBSupplyChainTradeAgreementType {

    @XmlElement(name = "SellerOrderReferencedCIReferencedDocument")
    protected CIReferencedDocumentType sellerOrderReferencedCIReferencedDocument;
    @XmlElement(name = "BuyerOrderReferencedCIReferencedDocument")
    protected CIReferencedDocumentType buyerOrderReferencedCIReferencedDocument;
    @XmlElement(name = "AdditionalReferencedCIReferencedDocument")
    protected List<CIReferencedDocumentType> additionalReferencedCIReferencedDocument;
    @XmlElement(name = "NetPriceProductCITradePrice", required = true)
    protected CITradePriceType netPriceProductCITradePrice;

    /**
     * sellerOrderReferencedCIReferencedDocumentプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIReferencedDocumentType }
     *     
     */
    public CIReferencedDocumentType getSellerOrderReferencedCIReferencedDocument() {
        return sellerOrderReferencedCIReferencedDocument;
    }

    /**
     * sellerOrderReferencedCIReferencedDocumentプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIReferencedDocumentType }
     *     
     */
    public void setSellerOrderReferencedCIReferencedDocument(CIReferencedDocumentType value) {
        this.sellerOrderReferencedCIReferencedDocument = value;
    }

    /**
     * buyerOrderReferencedCIReferencedDocumentプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIReferencedDocumentType }
     *     
     */
    public CIReferencedDocumentType getBuyerOrderReferencedCIReferencedDocument() {
        return buyerOrderReferencedCIReferencedDocument;
    }

    /**
     * buyerOrderReferencedCIReferencedDocumentプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIReferencedDocumentType }
     *     
     */
    public void setBuyerOrderReferencedCIReferencedDocument(CIReferencedDocumentType value) {
        this.buyerOrderReferencedCIReferencedDocument = value;
    }

    /**
     * Gets the value of the additionalReferencedCIReferencedDocument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalReferencedCIReferencedDocument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalReferencedCIReferencedDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIReferencedDocumentType }
     * 
     * 
     */
    public List<CIReferencedDocumentType> getAdditionalReferencedCIReferencedDocument() {
        if (additionalReferencedCIReferencedDocument == null) {
            additionalReferencedCIReferencedDocument = new ArrayList<CIReferencedDocumentType>();
        }
        return this.additionalReferencedCIReferencedDocument;
    }

    /**
     * netPriceProductCITradePriceプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CITradePriceType }
     *     
     */
    public CITradePriceType getNetPriceProductCITradePrice() {
        return netPriceProductCITradePrice;
    }

    /**
     * netPriceProductCITradePriceプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CITradePriceType }
     *     
     */
    public void setNetPriceProductCITradePrice(CITradePriceType value) {
        this.netPriceProductCITradePrice = value;
    }

}
