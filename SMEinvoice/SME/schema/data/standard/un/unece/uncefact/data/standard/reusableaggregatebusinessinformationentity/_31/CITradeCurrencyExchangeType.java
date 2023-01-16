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
import un.unece.uncefact.data.standard.unqualifieddatatype._31.DateTimeType;
import un.unece.uncefact.data.standard.unqualifieddatatype._31.RateType;


/**
 * <p>CITradeCurrencyExchangeType complex typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * 
 * <pre>
 * &lt;complexType name="CITradeCurrencyExchangeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SourceCurrencyCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType" minOccurs="0"/>
 *         &lt;element name="TargetCurrencyCode" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}CodeType" minOccurs="0"/>
 *         &lt;element name="ConversionRate" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}RateType" minOccurs="0"/>
 *         &lt;element name="ConversionRateDateTime" type="{urn:un:unece:uncefact:data:standard:UnqualifiedDataType:31}DateTimeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CITradeCurrencyExchangeType", propOrder = {
    "sourceCurrencyCode",
    "targetCurrencyCode",
    "conversionRate",
    "conversionRateDateTime"
})
public class CITradeCurrencyExchangeType {

    @XmlElement(name = "SourceCurrencyCode")
    protected CodeType sourceCurrencyCode;
    @XmlElement(name = "TargetCurrencyCode")
    protected CodeType targetCurrencyCode;
    @XmlElement(name = "ConversionRate")
    protected RateType conversionRate;
    @XmlElement(name = "ConversionRateDateTime")
    protected DateTimeType conversionRateDateTime;

    /**
     * sourceCurrencyCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getSourceCurrencyCode() {
        return sourceCurrencyCode;
    }

    /**
     * sourceCurrencyCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setSourceCurrencyCode(CodeType value) {
        this.sourceCurrencyCode = value;
    }

    /**
     * targetCurrencyCodeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    /**
     * targetCurrencyCodeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setTargetCurrencyCode(CodeType value) {
        this.targetCurrencyCode = value;
    }

    /**
     * conversionRateプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link RateType }
     *     
     */
    public RateType getConversionRate() {
        return conversionRate;
    }

    /**
     * conversionRateプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link RateType }
     *     
     */
    public void setConversionRate(RateType value) {
        this.conversionRate = value;
    }

    /**
     * conversionRateDateTimeプロパティの値を取得します。
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getConversionRateDateTime() {
        return conversionRateDateTime;
    }

    /**
     * conversionRateDateTimeプロパティの値を設定します。
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setConversionRateDateTime(DateTimeType value) {
        this.conversionRateDateTime = value;
    }

}
