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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.DateTimeType;


/**
 * <p>CISpecifiedPeriodType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CISpecifiedPeriodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StartDateTime" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}DateTimeType"/>
 *         &lt;element name="EndDateTime" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}DateTimeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CISpecifiedPeriodType", propOrder = {
    "startDateTime",
    "endDateTime"
})
public class CISpecifiedPeriodType {

    @XmlElement(name = "StartDateTime", required = true)
    protected DateTimeType startDateTime;
    @XmlElement(name = "EndDateTime", required = true)
    protected DateTimeType endDateTime;

    /**
     * startDateTimeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getStartDateTime() {
        return startDateTime;
    }

    /**
     * startDateTimeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setStartDateTime(DateTimeType value) {
        this.startDateTime = value;
    }

    /**
     * endDateTimeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getEndDateTime() {
        return endDateTime;
    }

    /**
     * endDateTimeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setEndDateTime(DateTimeType value) {
        this.endDateTime = value;
    }

}
