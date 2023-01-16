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
 * <p>CICreditorFinancialInstitutionType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CICreditorFinancialInstitutionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}TextType" minOccurs="0"/>
 *         &lt;element name="JapanFinancialInstitutionCommonID" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}IDType" minOccurs="0"/>
 *         &lt;element name="Sub-DivisionBranchFinancialInstitution" type="{urn:un:unece:uncefact:data:standard:ReusableAggregateBusinessInformationEntity:31}BranchFinancialInstitutionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CICreditorFinancialInstitutionType", propOrder = {
    "name",
    "japanFinancialInstitutionCommonID",
    "subDivisionBranchFinancialInstitution"
})
public class CICreditorFinancialInstitutionType {

    @XmlElement(name = "Name")
    protected TextType name;
    @XmlElement(name = "JapanFinancialInstitutionCommonID")
    protected IDType japanFinancialInstitutionCommonID;
    @XmlElement(name = "Sub-DivisionBranchFinancialInstitution")
    protected BranchFinancialInstitutionType subDivisionBranchFinancialInstitution;

    /**
     * nameプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getName() {
        return name;
    }

    /**
     * nameプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setName(TextType value) {
        this.name = value;
    }

    /**
     * japanFinancialInstitutionCommonIDプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getJapanFinancialInstitutionCommonID() {
        return japanFinancialInstitutionCommonID;
    }

    /**
     * japanFinancialInstitutionCommonIDプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setJapanFinancialInstitutionCommonID(IDType value) {
        this.japanFinancialInstitutionCommonID = value;
    }

    /**
     * subDivisionBranchFinancialInstitutionプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link BranchFinancialInstitutionType }
     *     
     */
    public BranchFinancialInstitutionType getSubDivisionBranchFinancialInstitution() {
        return subDivisionBranchFinancialInstitution;
    }

    /**
     * subDivisionBranchFinancialInstitutionプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link BranchFinancialInstitutionType }
     *     
     */
    public void setSubDivisionBranchFinancialInstitution(BranchFinancialInstitutionType value) {
        this.subDivisionBranchFinancialInstitution = value;
    }

}
