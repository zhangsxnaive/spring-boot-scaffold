package com.chmpay.idauth.common.contants;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangsx
 * @date 2018/9/18
 */
public class Contants {
    public static final String ZERO = "0";

    public static final String NFSURL = "https://data.chmpay.com/images/";


    public final static String SUPER_ROLE1_ID = "00000000001";

    public final static String SUPER_ROLE2_ID = "00000000002";
    public static final String ENTITY_OK = "0";
    public static final String ENTITY_DELETE = "1";
    public static final Long DEFAUALT_INVOICE_AMT = 10000L;
    /**
     * 开票状态 0 待开票 1 已开票 2 已配送 8 完成  9作废
     */
    public static final String INVOICE_NOT_INVOICED = "0";
    public static final String INVOICE_INVOICED = "1";
    public static final String INVOICE_SENT = "2";
    public static final String INVOICE_SUCCESS = "8";
    public static final String INVOICE_REVOKE = "9";

    /**
     * 充值
     */
    public static final String ORDER_TYPE_RECHARGE = "0";

    /**
     * 套餐购买
     */
    public static final String ORDER_TYPE_PACKAGE = "1";
    /**
     * 支付類型 0 綫下  1 綫上
     */
    public static final String[] PAY_TYPE_RECHARGE = {"0", "1", "2"};
    public static final String[] ONLINE_TYPE = {"0", "1"};
    public static final String[] PAY_TYPE_PACKAGE = {"0", "1", "2", "9"};
    /**
     * 0待付款、待提交 1 待审核 2审核不通过 3 待复核 4 复核不通过  9充值完成
     */
    public static final String STATUS_WAIT_PAY = "0";
    public static final String STATUS_WAIT_CHECK = "1";
    public static final String STATUS_CHECK_FAIL = "2";
    public static final String STATUS_CHECK = "3";
    public static final String STATUS_RECHECK_FAIL = "4";
    public static final String STATUS_FAIL = "8";
    public static final String STATUS_SUCCESS = "9";

    public static final String[] FREEZE_AMT_STATUS = {STATUS_CHECK, STATUS_CHECK};
    /**
     * 余额
     */
    public static final String PAY_TYPE_BALANCE = "9";
    /**
     * 支付宝
     */
    public static final String PAY_TYPE_ALIPAY = "0";
    /**
     * 微信
     */
    public static final String PAY_TYPE_WECHAT = "1";
    /**
     * 银行卡
     */
    public static final String PAY_TYPE_BANK = "2";
    /**
     * 两项数据
     */
    public static final int PRODUCT_10000 = 10000;
    /**
     * 两项数据+照片
     */
    public static final int PRODUCT_10001 = 10001;
    /**
     * 四项数据
     */
    public static final int PRODUCT_10002 = 10002;
    /**
     * 四项数据+照片
     */
    public static final int PRODUCT_10003 = 10003;
    public static final int PRODUCT_10004 = 10004;
    public static final int PRODUCT_10005 = 10005;
    public static final int PRODUCT_10006 = 10006;
    public static final int PRODUCT_10007 = 10007;

    /**
     * 身份认证类型
     */
    public static String AUTH_TYPE_FOUR = "0";
    public static String AUTH_TYPE_FOUR_FACE = "1";
    public static String AUTH_TYPE_TWO = "2";
    public static String AUTH_TYPE_TWO_FACE = "3";
    public static String AUTH_TYPE_SESSION_CODE = "4";
    public static String AUTH_TYPE_LIVE_DETECTION = "5";
    public static String AUTH_TYPE_ID_OCR = "6";
    public static String AUTH_TYPE_BANK_OCR = "7";


    public static String REDIS_PRFIX = "IDAUTH";
    public static String IDAUTH_APPID = "idauth_appid";
    public static String IDAUTH_ROLE_ADMIN = "ADMIN";
    public static String IDAUTH_ROLE_USER = "USER";

    public static String HTTP_METHOD_OPTIONS = "OPTIONS";
    public static int HTTP_MAXCONTENTLENGTH = 1024 * 1024 * 10;

    /**
     * 超时时间 两小时
     */
    public static final int LOGON_STAY = 60 * 60 * 2;
    /**
     * 五分钟
     */
    public static final int VALID_TIMEOUT = 60 * 5;
    /**
     * 一天
     */
    public static final int NORMAL_TIMEOUT = 60 * 60 * 24;
    /**
     * 超管 二级审核
     */
    public static final String ROLE_TYPE_SUPER = "super";
    /**
     * 普通管理员 一级审核
     */
    public static final String ROLE_TYPE_ADMIN = "admin";
    /**
     * 合约用户
     */
    public static final String ROLE_TYPE_AGREEMENT = "agreement";

    /**
     * 后付费合约用户
     */
    public static final String ROLE_TYPE_POST_AGREEMENT = "post-agreement";

    /**
     * 代理商
     */
    public static final String ROLE_TYPE_AGENT = "agent";

    /**
     * 普通用戶
     */
    public static final String ROLE_TYPE_NORMAL = "normal";
    /**
     * 客服
     */
    public static final String ROLE_TYPE_SERVICE = "service";

    public static final String[] NORMAL_INVOICE = new String[]{
            "序号",
            "名称",
            "纳税人识别号",
            "发票金额（元）",
            "收件人姓名",
            "收件人联系电话",
            "收件地址",
            "导出日期"};
    public static final String[] PROFESSION_INVOICE = new String[]{
            "序号",
            "名称",
            "纳税人识别号",
            "地址",
            "电话",
            "开户银行",
            "卡户账号",
            "发票金额（元）",
            "收件人姓名",
            "收件人联系电话",
            "收件地址",
            "导出日期"
    };

    public static final String[] ADMIN_TYPE = new String[]{ROLE_TYPE_SUPER, ROLE_TYPE_ADMIN, ROLE_TYPE_SERVICE};

    public static final String[] ADMIN_TYPE_NO_SERVICE = new String[]{ROLE_TYPE_SUPER, ROLE_TYPE_ADMIN};
    
    
    public static final String[] SETTLE_ROTYPE = {"post-agreement", "POST-AGREEMENT"
			, "agreement","AGREEMENT"
			, "normal","NORMAL"};

    /**
     * 合约用户，包括预付费和后付费
     */
    public static final String[] USER_TYPE_AGREEMENT = {ROLE_TYPE_AGREEMENT, ROLE_TYPE_POST_AGREEMENT};

    public static final String[] USER_TYPE = new String[]{ROLE_TYPE_AGREEMENT, ROLE_TYPE_NORMAL};


    public static final Set<String> IDENTIFY_TYPE;


    public static final String MSG_TYPE_RECHARGE_NOTICE = "充值已提交";

    public static final String MSG_RECHARGE_MESSAGE = "您的充值信息已经提交审核，我们将会在一个工作日内对您的信息进行审核，审核完毕后，充值金额将会实时到账。";


    public static final String MSG_TYPE_RECHARGE_CHECK = "充值审核通过";

    public static final String MSG_CHECK_RECHARGE_MESSAGE = "您的充值金额已经审核通过，充值金额已经转入到您的账户余额，您可以直接使用账户余额购买产品套餐";

    public static final String MSG_TYPE_RECHARGE_OK = "充值完成";
    public static final String MSG_RECHARGE_OK_MESSAGE = "您于yyyy年MM月dd日HH:mm分成功充值金额amt元";

    public static final String MSG_TYPE_REGISTER = "注册成功";
    public static final String MSG_REGISTER_MESSAGE = "您已注册成功，请尽快进行企业认证";

    public static final String MSG_TYPE_BUY_PACKAGE = "购买套餐";
    public static final String MSG_BUY_PACKAGE_MESSAGE = "您购买的套餐已经提交审核，我们将会在一个工作日内对您的信息进行审核，审核完毕后，您购买的套餐将会立即生效";

    public static final String MSG_TYPE_BUY_PACKAGE_OK = "购买套餐成功";
    public static final String MSG_BUY_PACKAGE_OK_MESSAGE = "您于yyyy年MM月dd日HH:mm分成功购买packageName套餐，套餐已生效";
    
    public static final String MSG_TYPE_BUY_PACKAGE_FAIL = "购买套餐失败";
    public static final String MSG_BUY_PACKAGE_FAIL_MESSAGE = "您于yyyy年MM月dd日HH:mm分成功购买packageName套餐，套餐未审核通过，如有疑问,请联系客服人员";

    public static final String MSG_TYPE_INVOICE_OPEN = "开票提交";
    public static final String MSG_INVOICE_OPEN_MESSAGE = "您于yyyy年MM月dd日申请的发票正在开票中";

    public static final String MSG_TYPE_INVOICE_DELIVERY = "开票配送";
    public static final String MSG_INVOICE_DELIVERY_MESSAGE = "您于yyyy年MM月dd日申请的发票正在邮寄中,deliveryCompany，单号deliveryNo";

    /**
     * //1.企业认证提交后审核复核
     */
    public static final String TODO_ADD_ENTERPRISE = "1";
    /**
     * 2.线下充值转账至对公账户
     */
    public static final String TODO_OFFLINE_RECHARGE = "2";

    /**
     * 3.线下购买转账至对公账户
     */
    public static final String TODO_OFFLINE_PACKAGE = "3";
    /**
     * 4.创建套餐时
     */
    public static final String TODO_CREATE_PACKAGE = "4";

    /**
     * 5.创建新的梯度
     */
    public static final String TODO_CREATE_GRADIENT = "5";

    /**
     * 6.梯度配置时
     */
    public static final String TODO_SET_GRADIENT = "6";
    
    /**
     * 7.营销配置（免费赠送）
     */
    public static final String TODO_GIVE_USER = "7";
    
    /**
     * 8 合约即将到期提醒
     */
    public static final String GRADIENT_REMIND = "8";


    /**
     * 待处理
     */
    public static final String TODO_STATUS_WAIT = "0";

    /**
     * 已处理
     */
    public static final String TODO_STATUS_SUC = "1";
    /**
     * 处理失败
     */
    public static final String TODO_STATUS_FAIL = "2";


    /**
     * 1级审核
     */
    public static final String STEP_1 = "1";
    /**
     * 2级审核
     */
    public static final String STEP_2 = "2";

    public static final Map<String, Integer> PRODUCT_MAP;

    public static final Map<String,String> PRODUCT_AUTH_TYPE;

    public static final Set<String> EFFECTIVE_CODE = new HashSet<>();

    static {
        PRODUCT_MAP = new HashMap<>();
        PRODUCT_MAP.put(AUTH_TYPE_TWO, PRODUCT_10000);
        PRODUCT_MAP.put(AUTH_TYPE_TWO_FACE, PRODUCT_10001);
        PRODUCT_MAP.put(AUTH_TYPE_FOUR, PRODUCT_10002);
        PRODUCT_MAP.put(AUTH_TYPE_FOUR_FACE, PRODUCT_10003);
        PRODUCT_MAP.put(AUTH_TYPE_SESSION_CODE, PRODUCT_10004);
        PRODUCT_MAP.put(AUTH_TYPE_LIVE_DETECTION, PRODUCT_10005);
        PRODUCT_MAP.put(AUTH_TYPE_ID_OCR, PRODUCT_10006);
        PRODUCT_MAP.put(AUTH_TYPE_BANK_OCR, PRODUCT_10007);


        PRODUCT_AUTH_TYPE = new HashMap<>();
        PRODUCT_AUTH_TYPE.put(PRODUCT_10000 + "", AUTH_TYPE_TWO);
        PRODUCT_AUTH_TYPE.put(PRODUCT_10001 + "", AUTH_TYPE_TWO_FACE);
        PRODUCT_AUTH_TYPE.put(PRODUCT_10002 + "", AUTH_TYPE_FOUR);
        PRODUCT_AUTH_TYPE.put(PRODUCT_10003 + "", AUTH_TYPE_FOUR_FACE);
        PRODUCT_AUTH_TYPE.put(PRODUCT_10004 + "", AUTH_TYPE_SESSION_CODE);
        PRODUCT_AUTH_TYPE.put(PRODUCT_10005 + "", AUTH_TYPE_LIVE_DETECTION);
        PRODUCT_AUTH_TYPE.put(PRODUCT_10006 + "", AUTH_TYPE_ID_OCR);
        PRODUCT_AUTH_TYPE.put(PRODUCT_10007 + "", AUTH_TYPE_BANK_OCR);

        IDENTIFY_TYPE = new HashSet<>();
        IDENTIFY_TYPE.add(AUTH_TYPE_TWO);
        IDENTIFY_TYPE.add(AUTH_TYPE_TWO_FACE);
        IDENTIFY_TYPE.add(AUTH_TYPE_FOUR);
        IDENTIFY_TYPE.add(AUTH_TYPE_FOUR_FACE);

        EFFECTIVE_CODE.add("0X");
        EFFECTIVE_CODE.add("5X");
        EFFECTIVE_CODE.add("6X");
        EFFECTIVE_CODE.add("00");
        EFFECTIVE_CODE.add("01");
        EFFECTIVE_CODE.add("02");
        EFFECTIVE_CODE.add("0E");
        EFFECTIVE_CODE.add("0F");
        EFFECTIVE_CODE.add("0G");
        EFFECTIVE_CODE.add("0I");
        EFFECTIVE_CODE.add("0J");
        EFFECTIVE_CODE.add("0H");

    }

}
