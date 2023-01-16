//
// このファイルは、JavaTM Architecture for XML Binding(JAXB) Reference Implementation、v2.2.8-b130911.1802によって生成されました 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>を参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2023.01.16 時間 12:39:23 PM JST 
//


package un.unece.uncefact.data.standard.smeinvoice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._31.CIExchangedDocumentContextType;
import un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._31.CIIHExchangedDocumentType;
import un.unece.uncefact.data.standard.reusableaggregatebusinessinformationentity._31.CIIHSupplyChainTradeTransactionType;


/**
 * <p>SMEinvoiceType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="SMEinvoiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CIExchangedDocumentContext" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIExchangedDocumentContextType"/>
 *         &lt;element name="CIIHExchangedDocument" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIIHExchangedDocumentType"/>
 *         &lt;element name="CIIHSupplyChainTradeTransaction" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIIHSupplyChainTradeTransactionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SMEinvoiceType", propOrder = {
    "ciExchangedDocumentContext",
    "ciihExchangedDocument",
    "ciihSupplyChainTradeTransaction"
})
public class SMEinvoiceType {

    @XmlElement(name = "CIExchangedDocumentContext", required = true)
    protected CIExchangedDocumentContextType ciExchangedDocumentContext;
    @XmlElement(name = "CIIHExchangedDocument", required = true)
    protected CIIHExchangedDocumentType ciihExchangedDocument;
    @XmlElement(name = "CIIHSupplyChainTradeTransaction", required = true)
    protected CIIHSupplyChainTradeTransactionType ciihSupplyChainTradeTransaction;

    /**
     * ciExchangedDocumentContextプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIExchangedDocumentContextType }
     *     
     */
    public CIExchangedDocumentContextType getCIExchangedDocumentContext() {
        return ciExchangedDocumentContext;
    }

    /**
     * ciExchangedDocumentContextプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIExchangedDocumentContextType }
     *     
     */
    public void setCIExchangedDocumentContext(CIExchangedDocumentContextType value) {
        this.ciExchangedDocumentContext = value;
    }

    /**
     * ciihExchangedDocumentプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIIHExchangedDocumentType }
     *     
     */
    public CIIHExchangedDocumentType getCIIHExchangedDocument() {
        return ciihExchangedDocument;
    }

    /**
     * ciihExchangedDocumentプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIIHExchangedDocumentType }
     *     
     */
    public void setCIIHExchangedDocument(CIIHExchangedDocumentType value) {
        this.ciihExchangedDocument = value;
    }

    /**
     * ciihSupplyChainTradeTransactionプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIIHSupplyChainTradeTransactionType }
     *     
     */
    public CIIHSupplyChainTradeTransactionType getCIIHSupplyChainTradeTransaction() {
        return ciihSupplyChainTradeTransaction;
    }

    /**
     * ciihSupplyChainTradeTransactionプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIIHSupplyChainTradeTransactionType }
     *     
     */
    public void setCIIHSupplyChainTradeTransaction(CIIHSupplyChainTradeTransactionType value) {
        this.ciihSupplyChainTradeTransaction = value;
    }

}
