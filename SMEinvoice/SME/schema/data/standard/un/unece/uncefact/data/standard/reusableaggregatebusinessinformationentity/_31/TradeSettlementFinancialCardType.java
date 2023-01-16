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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.CodeType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.IDType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.TextType;


/**
 * <p>TradeSettlementFinancialCardType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="TradeSettlementFinancialCardType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType" minOccurs="0"/>
 *         &lt;element name="TypeCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType" minOccurs="0"/>
 *         &lt;element name="CardholderName" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType"/>
 *         &lt;element name="IssuingCompanyName" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradeSettlementFinancialCardType", propOrder = {
    "id",
    "typeCode",
    "cardholderName",
    "issuingCompanyName"
})
public class TradeSettlementFinancialCardType {

    @XmlElement(name = "ID")
    protected IDType id;
    @XmlElement(name = "TypeCode")
    protected CodeType typeCode;
    @XmlElement(name = "CardholderName", required = true)
    protected TextType cardholderName;
    @XmlElement(name = "IssuingCompanyName")
    protected TextType issuingCompanyName;

    /**
     * idプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getID() {
        return id;
    }

    /**
     * idプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setID(IDType value) {
        this.id = value;
    }

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
     * cardholderNameプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getCardholderName() {
        return cardholderName;
    }

    /**
     * cardholderNameプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setCardholderName(TextType value) {
        this.cardholderName = value;
    }

    /**
     * issuingCompanyNameプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getIssuingCompanyName() {
        return issuingCompanyName;
    }

    /**
     * issuingCompanyNameプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setIssuingCompanyName(TextType value) {
        this.issuingCompanyName = value;
    }

}
