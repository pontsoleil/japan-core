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
 * <p>CITradeAddressType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CITradeAddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PostcodeCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType" minOccurs="0"/>
 *         &lt;element name="LineOne" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *         &lt;element name="LineTwo" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *         &lt;element name="LineThree" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *         &lt;element name="CountryID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CITradeAddressType", propOrder = {
    "postcodeCode",
    "lineOne",
    "lineTwo",
    "lineThree",
    "countryID"
})
public class CITradeAddressType {

    @XmlElement(name = "PostcodeCode")
    protected CodeType postcodeCode;
    @XmlElement(name = "LineOne")
    protected TextType lineOne;
    @XmlElement(name = "LineTwo")
    protected TextType lineTwo;
    @XmlElement(name = "LineThree")
    protected TextType lineThree;
    @XmlElement(name = "CountryID", required = true)
    protected IDType countryID;

    /**
     * postcodeCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getPostcodeCode() {
        return postcodeCode;
    }

    /**
     * postcodeCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setPostcodeCode(CodeType value) {
        this.postcodeCode = value;
    }

    /**
     * lineOneプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getLineOne() {
        return lineOne;
    }

    /**
     * lineOneプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setLineOne(TextType value) {
        this.lineOne = value;
    }

    /**
     * lineTwoプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getLineTwo() {
        return lineTwo;
    }

    /**
     * lineTwoプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setLineTwo(TextType value) {
        this.lineTwo = value;
    }

    /**
     * lineThreeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getLineThree() {
        return lineThree;
    }

    /**
     * lineThreeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setLineThree(TextType value) {
        this.lineThree = value;
    }

    /**
     * countryIDプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getCountryID() {
        return countryID;
    }

    /**
     * countryIDプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setCountryID(IDType value) {
        this.countryID = value;
    }

}
