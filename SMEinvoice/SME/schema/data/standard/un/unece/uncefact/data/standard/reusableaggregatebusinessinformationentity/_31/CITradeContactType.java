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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.IDType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.TextType;


/**
 * <p>CITradeContactType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CITradeContactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType" minOccurs="0"/>
 *         &lt;element name="PersonName" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *         &lt;element name="DepartmentName" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *         &lt;element name="PersonID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType" minOccurs="0"/>
 *         &lt;element name="TelephoneCIUniversalCommunication" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIUniversalCommunicationType" minOccurs="0"/>
 *         &lt;element name="FaxCIUniversalCommunication" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIUniversalCommunicationType" minOccurs="0"/>
 *         &lt;element name="EmailURICIUniversalCommunication" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}CIUniversalCommunicationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CITradeContactType", propOrder = {
    "id",
    "personName",
    "departmentName",
    "personID",
    "telephoneCIUniversalCommunication",
    "faxCIUniversalCommunication",
    "emailURICIUniversalCommunication"
})
public class CITradeContactType {

    @XmlElement(name = "ID")
    protected IDType id;
    @XmlElement(name = "PersonName")
    protected TextType personName;
    @XmlElement(name = "DepartmentName")
    protected TextType departmentName;
    @XmlElement(name = "PersonID")
    protected IDType personID;
    @XmlElement(name = "TelephoneCIUniversalCommunication")
    protected CIUniversalCommunicationType telephoneCIUniversalCommunication;
    @XmlElement(name = "FaxCIUniversalCommunication")
    protected CIUniversalCommunicationType faxCIUniversalCommunication;
    @XmlElement(name = "EmailURICIUniversalCommunication")
    protected CIUniversalCommunicationType emailURICIUniversalCommunication;

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
     * personNameプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getPersonName() {
        return personName;
    }

    /**
     * personNameプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setPersonName(TextType value) {
        this.personName = value;
    }

    /**
     * departmentNameプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getDepartmentName() {
        return departmentName;
    }

    /**
     * departmentNameプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setDepartmentName(TextType value) {
        this.departmentName = value;
    }

    /**
     * personIDプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getPersonID() {
        return personID;
    }

    /**
     * personIDプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setPersonID(IDType value) {
        this.personID = value;
    }

    /**
     * telephoneCIUniversalCommunicationプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIUniversalCommunicationType }
     *     
     */
    public CIUniversalCommunicationType getTelephoneCIUniversalCommunication() {
        return telephoneCIUniversalCommunication;
    }

    /**
     * telephoneCIUniversalCommunicationプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIUniversalCommunicationType }
     *     
     */
    public void setTelephoneCIUniversalCommunication(CIUniversalCommunicationType value) {
        this.telephoneCIUniversalCommunication = value;
    }

    /**
     * faxCIUniversalCommunicationプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIUniversalCommunicationType }
     *     
     */
    public CIUniversalCommunicationType getFaxCIUniversalCommunication() {
        return faxCIUniversalCommunication;
    }

    /**
     * faxCIUniversalCommunicationプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIUniversalCommunicationType }
     *     
     */
    public void setFaxCIUniversalCommunication(CIUniversalCommunicationType value) {
        this.faxCIUniversalCommunication = value;
    }

    /**
     * emailURICIUniversalCommunicationプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CIUniversalCommunicationType }
     *     
     */
    public CIUniversalCommunicationType getEmailURICIUniversalCommunication() {
        return emailURICIUniversalCommunication;
    }

    /**
     * emailURICIUniversalCommunicationプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CIUniversalCommunicationType }
     *     
     */
    public void setEmailURICIUniversalCommunication(CIUniversalCommunicationType value) {
        this.emailURICIUniversalCommunication = value;
    }

}
