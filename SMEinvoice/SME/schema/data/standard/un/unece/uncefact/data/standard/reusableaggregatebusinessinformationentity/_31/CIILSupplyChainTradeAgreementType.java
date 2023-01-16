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


/**
 * <p>CIILSupplyChainTradeAgreementType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CIILSupplyChainTradeAgreementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SellerOrderReferencedCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" minOccurs="0"/>
 *         &lt;element name="BuyerOrderReferencedCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" minOccurs="0"/>
 *         &lt;element name="ContractReferencedCIReferencedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIReferencedDocumentType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIILSupplyChainTradeAgreementType", propOrder = {
    "sellerOrderReferencedCIReferencedDocument",
    "buyerOrderReferencedCIReferencedDocument",
    "contractReferencedCIReferencedDocument"
})
public class CIILSupplyChainTradeAgreementType {

    @XmlElement(name = "SellerOrderReferencedCIReferencedDocument")
    protected CIReferencedDocumentType sellerOrderReferencedCIReferencedDocument;
    @XmlElement(name = "BuyerOrderReferencedCIReferencedDocument")
    protected CIReferencedDocumentType buyerOrderReferencedCIReferencedDocument;
    @XmlElement(name = "ContractReferencedCIReferencedDocument")
    protected CIReferencedDocumentType contractReferencedCIReferencedDocument;

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
     * contractReferencedCIReferencedDocumentプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIReferencedDocumentType }
     *     
     */
    public CIReferencedDocumentType getContractReferencedCIReferencedDocument() {
        return contractReferencedCIReferencedDocument;
    }

    /**
     * contractReferencedCIReferencedDocumentプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIReferencedDocumentType }
     *     
     */
    public void setContractReferencedCIReferencedDocument(CIReferencedDocumentType value) {
        this.contractReferencedCIReferencedDocument = value;
    }

}
