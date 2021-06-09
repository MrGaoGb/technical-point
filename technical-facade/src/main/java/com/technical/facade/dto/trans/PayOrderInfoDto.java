package com.technical.facade.dto.trans;

import com.technical.comm.annotation.ExportExcel;
import com.technical.facade.dto.TcBaseEntityDto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PayOrderInf - 代付交易信息
 *
 * @author gaogba
 * @version 1.0
 * @date 2020-07-29
 */
public class PayOrderInfoDto extends TcBaseEntityDto {

    private static final long serialVersionUID = 6477029282324776700L;
    /**
     * 字段
     */

    // 下游订单号
    @ExportExcel(colName = "代理商订单号", order = 4)
    private String orderNo;
    // 税筹订单号
    @ExportExcel(colName = "平台订单号", order = 5)
    private String platformOrderNo;
    // 渠道订单号
    @ExportExcel(colName = "渠道订单号", order = 6)
    private String chlOrderId;

    // 渠道编号
    private String channelNo;

    @ExportExcel(colName = "支付通道", order = 7)
    private String payChannelDesc;
    // 渠道代理商编号
    @ExportExcel(colName = "渠道代理商编号", order = 8)
    private String chlMerchantCode;
    //代理商编号
    @ExportExcel(colName = "代理商编号", order = 1)
    private String agentCodeSub;
    // 分公司编号
    @ExportExcel(colName = "一代编号", order = 2)
    private String agentCode;
    // 渠道分公司编号
    @ExportExcel(colName = "渠道一代编号", order = 3)
    private String chlAgentCode;

    // 提现金额
    @ExportExcel(colName = "提现金额", order = 9)
    private BigDecimal amount;
    // 收款人手机号掩码
    @ExportExcel(colName = "手机号", order = 10)
    private String phoneMask;

    // 收款人手机号密文
    private String phoneCipher;
    //收款人手机号明文
    private String phoneNo;

    // 收款人卡号掩码
    @ExportExcel(colName = "收款人卡号", order = 11)
    private String cardNoMask;
    // 收款人卡号密文
    private String cardNoCipher;

    //收款人卡号明文
    private String cardNo;
    // 收款行名称
    @ExportExcel(colName = "收款行名称", order = 12)
    private String bankName;
    //银行code
    private String bankChannelCode;
    // 支付渠道服务费
    @ExportExcel(colName = "支付渠道服务费", order = 13)
    private BigDecimal channelFee;
    // 平台服务费
    @ExportExcel(colName = "平台服务费", order = 14)
    private BigDecimal platformFee;
    // 支付总金额
    @ExportExcel(colName = "支付总金额", order = 15)
    private BigDecimal totalAmount;

    // 订单状态0未支付；1支付中；2支付成功；3支付失败
    private Integer orderStatus;

    @ExportExcel(colName = "订单状态", order = 16)
    private String orderStatusDesc;

    // 交易信息
    private String message;
    // 到账时间
    private Date accountingTime;

    @ExportExcel(colName = "到账时间", order = 17)
    private String accountDate;
    // 代付订单回调地址
    private String notifyUrl;
    // 备注
    @ExportExcel(colName = "备注", order = 18)
    private String remark;
    // 创建时间
    private Date createTime;

    @ExportExcel(colName = "创建时间", order = 19)
    private String createDate;
    // 更新时间
    private Date updateTime;
    // 0：未推送   1：已推送
    private Integer checkStatus;
    // 订单发起时间
    private Date requestTime;


    /**
     * Getter Setter Method
     */
    /**
     * 下游订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 下游订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 税筹订单号
     */
    public String getPlatformOrderNo() {
        return platformOrderNo;
    }

    /**
     * 税筹订单号
     */
    public void setPlatformOrderNo(String platformOrderNo) {
        this.platformOrderNo = platformOrderNo;
    }

    /**
     * 渠道订单号
     */
    public String getChlOrderId() {
        return chlOrderId;
    }

    /**
     * 渠道订单号
     */
    public void setChlOrderId(String chlOrderId) {
        this.chlOrderId = chlOrderId;
    }

    /**
     * 渠道用户编号
     */
    public String getChlMerchantCode() {
        return chlMerchantCode;
    }

    /**
     * 渠道用户编号
     */
    public void setChlMerchantCode(String chlMerchantCode) {
        this.chlMerchantCode = chlMerchantCode;
    }

    /**
     *
     */
    public String getAgentCode() {
        return agentCode;
    }

    /**
     *
     */
    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    /**
     * 渠道代理商编号
     */
    public String getChlAgentCode() {
        return chlAgentCode;
    }

    /**
     * 渠道代理商编号
     */
    public void setChlAgentCode(String chlAgentCode) {
        this.chlAgentCode = chlAgentCode;
    }

    /**
     * 提现金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 提现金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 收款人手机号掩码
     */
    public String getPhoneMask() {
        return phoneMask;
    }

    /**
     * 收款人手机号掩码
     */
    public void setPhoneMask(String phoneMask) {
        this.phoneMask = phoneMask;
    }

    /**
     * 收款人手机号密文
     */
    public String getPhoneCipher() {
        return phoneCipher;
    }

    /**
     * 收款人手机号密文
     */
    public void setPhoneCipher(String phoneCipher) {
        this.phoneCipher = phoneCipher;
    }

    /**
     * 收款人卡号掩码
     */
    public String getCardNoMask() {
        return cardNoMask;
    }

    /**
     * 收款人卡号掩码
     */
    public void setCardNoMask(String cardNoMask) {
        this.cardNoMask = cardNoMask;
    }

    /**
     * 收款人卡号密文
     */
    public String getCardNoCipher() {
        return cardNoCipher;
    }

    /**
     * 收款人卡号密文
     */
    public void setCardNoCipher(String cardNoCipher) {
        this.cardNoCipher = cardNoCipher;
    }

    /**
     * 收款行名称
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 收款行名称
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * 收款行银行编码
     */
    public String getBankChannelCode() {
        return bankChannelCode;
    }

    /**
     * 收款行银行编码
     */
    public void setBankChannelCode(String bankChannelCode) {
        this.bankChannelCode = bankChannelCode;
    }

    /**
     * 支付渠道服务费
     */
    public BigDecimal getChannelFee() {
        return channelFee;
    }

    /**
     * 支付渠道服务费
     */
    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }

    /**
     * 平台服务费
     */
    public BigDecimal getPlatformFee() {
        return platformFee;
    }

    /**
     * 平台服务费
     */
    public void setPlatformFee(BigDecimal platformFee) {
        this.platformFee = platformFee;
    }

    /**
     * 支付总金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 支付总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 订单状态0未支付；1支付中；2支付成功；3支付失败
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 订单状态0未支付；1支付中；2支付成功；3支付失败
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 交易信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 交易信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 到账时间
     */
    public Date getAccountingTime() {
        return accountingTime;
    }

    /**
     * 到账时间
     */
    public void setAccountingTime(Date accountingTime) {
        this.accountingTime = accountingTime;
    }

    /**
     * 代付订单回调地址
     */
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /**
     * 代付订单回调地址
     */
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 0：未推送   1：已推送
     */
    public Integer getCheckStatus() {
        return checkStatus;
    }

    /**
     * 0：未推送   1：已推送
     */
    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * 订单发起时间
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * 订单发起时间
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * 手机号
     *
     * @return
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * 收款人卡号明文
     *
     * @return
     */
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAgentCodeSub() {
        return agentCodeSub;
    }

    public void setAgentCodeSub(String agentCodeSub) {
        this.agentCodeSub = agentCodeSub;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    public String getPayChannelDesc() {
        return payChannelDesc;
    }

    public void setPayChannelDesc(String payChannelDesc) {
        this.payChannelDesc = payChannelDesc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PayOrderInf [");
        sb.append("id=").append(getId()).append(",");
        sb.append("orderNo=").append(getOrderNo()).append(",");
        sb.append("platformOrderNo=").append(getPlatformOrderNo()).append(",");
        sb.append("chlOrderId=").append(getChlOrderId()).append(",");
        sb.append("chlMerchantCode=").append(getChlMerchantCode()).append(",");
        sb.append("agentCode=").append(getAgentCode()).append(",");
        sb.append("chlAgentCode=").append(getChlAgentCode()).append(",");
        sb.append("amount=").append(getAmount()).append(",");
        sb.append("phoneMask=").append(getPhoneMask()).append(",");
        sb.append("phoneCipher=").append(getPhoneCipher()).append(",");
        sb.append("cardNoMask=").append(getCardNoMask()).append(",");
        sb.append("cardNoCipher=").append(getCardNoCipher()).append(",");
        sb.append("bankName=").append(getBankName()).append(",");
        sb.append("bankChannelCode=").append(getBankChannelCode()).append(",");
        sb.append("channelFee=").append(getChannelFee()).append(",");
        sb.append("platformFee=").append(getPlatformFee()).append(",");
        sb.append("totalAmount=").append(getTotalAmount()).append(",");
        sb.append("orderStatus=").append(getOrderStatus()).append(",");
        sb.append("message=").append(getMessage()).append(",");
        sb.append("accountingTime=").append(getAccountingTime()).append(",");
        sb.append("notifyUrl=").append(getNotifyUrl()).append(",");
        sb.append("remark=").append(getRemark()).append(",");
        sb.append("createTime=").append(getCreateTime()).append(",");
        sb.append("updateTime=").append(getUpdateTime()).append(",");
        sb.append("deleteStatus=").append(getDeleteStatus()).append(",");
        sb.append("checkStatus=").append(getCheckStatus()).append(",");
        sb.append("requestTime=").append(getRequestTime()).append(",");
        sb.append("agentCodeSub=").append(getAgentCodeSub());
        sb.append("]");
        return sb.toString();
    }
}
