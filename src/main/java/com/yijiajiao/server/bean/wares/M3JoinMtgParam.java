package com.yijiajiao.server.bean.wares;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class M3JoinMtgParam {

    private Integer waresId;
    private Integer slaveId;

    private String siteId;//站点标识最大长度 16
    private String mtgKey;// Not Null,课堂的唯一标识最大长度 64 位(请参考 3.7 接口统一说明)
    private String mtgTitle;//  Not Null,课堂名称或者课堂主题最大长度 32，不能有特殊字符。
    /**
     * Not Null;
     * 课堂的开始时间，作为课堂结束气泡提示的"参考"时间。
     * 格式为: YYYY-MM-DD HH:MM:SS
     * 注：start 时间比现在时间早，而endTime时间也早于现在时间，课堂也是可以创建的。
     */
    private String startTime;
    private String endTime;
    /**
     * 可选, 持续时长
     */
    private Integer duration;

    private Integer language;// 可选，客户端语言：1、英文; 2、中文；默认为 2

    /**
     * Not Null;
     * 客户端显示的姓名，最大长度32
     */
    private String userName;
    private String userId;// Not Null 无 英文或数字组成的字符串，最大长度 50,userId=0是匿名用户,可以允许多个用户同时存在
    // web
    private Integer userType;// Not Null 无 用户类型：1：主持人（所有权限）2：主讲人（不能操作摄像头）8：普通与会者32: 监课人员

    /**
     * 1、互动;
     * 2、直播；
     * 默认为 1;
     * 互动课堂支持多人音视频，适用于小班授课；
     * 直播课堂适用于千人以上大课堂，不能进行音视频互动，支持 H5 网页观看 。
     */
    private Integer meetingType;

    /**
     * Not Null;
     * 主持人密码，密码长度 6 到 8 位，用于对普通人员申请主持人时的验证
     */
    private String hostPwd;

    private String docID;// 可选 无 文档唯一标识，多个标识用英文逗号分隔，文档唯一标识通过查询文档列表接口获取
    private String mediaID;// 可选 无 伴音唯一标识，多个标识用英文逗号分隔，伴音唯一标识通过查询伴音列表接口获取
    private String backUrl;// 可选 无 课堂结束后回调地址

    private Long timestamp;// Not Null 无 服务器时间戳：（第一个接口获取的时间戳）

    private Integer docModule; //可选, 同站点默认值,文档共享是否启用（1 启用，其他禁止）,不传默认为站点默认值。

    /**
     * 可选, 屏幕共享是否启用（1 启用，其他禁止）,
     * // 如果站点开启屏幕共享功能，传什么值就是什么值，不传就是站点默认值。
     * // 如果站点不开启屏幕共享功能不论传不传都是禁止的
     */
    private Integer screenModule;

    //媒体共享是否启用（1 启用，其他禁止），如果站点开启媒体共享功能，
    // 传什么值就是什么值，不传就是站点默认值。如果站点不开启媒体共享功能不论传不传都是禁止的
    private Integer mediaModule;

    /**
     * 可选, 站点默认值, 白板是否启用（1 启用，其他禁止）
     */
    private Integer whiteboardModule;

    /**
     * 可选 无 录制是否启用（1 启用，其他禁止），
     * <p>
     * // 如果站点开启录制功能，传什么值就是什么值，不传就是站点默认值。
     * // 如果站点不开启录制功能不论传不传都是禁止的
     */
    private Integer recordModule;

    /**
     * 可选 无 视频是否启用（1 启用，其他禁止），
     * // 如果站点开启视频功能，如果传什么值就是什么值，不传就是站点默认值。
     * // 如果站点不开启视频功能不论传不传都是禁用的
     */
    private Integer videoModule;

    /**
     * 如果站点h5开关开启，传开启就是开启状态、传禁用就是禁用;
     * 否则无论传什么都是禁用，默认不传时为禁用状态;
     * 可选, 默认：0;
     */
    private Integer h5Module;

    /**
     * 可选, 是否自动录制：（1 是，0 否）
     * 默认值:站点默认值
     */
    private Integer autoRecord;

    /**
     * 是否参与音视频互动： （1 是，0 否）
     * 可选 站点默认值
     */
    private Integer interaction;

    /**
     * 最大音频路数;
     * 可选;
     * 如果大于站点最大路数，默认为站点最大路数，
     * 如果不传，默认为站点路数,直播课堂只支持 1 路
     */
    private Integer maxAudioChannels;

    /**
     * 最大视频路数,可选;
     * 如果大于站点路数，默认为站点最大路数，
     * 如果不传，默认为站点路数直播课堂只支持 1 路
     */
    private Integer maxVideoChannels;
    /**
     * 可选,默认0, 视频画质设置（低 0，中 1，高2，较高 3，最高 4）
     */
    private Integer videoQuality;

    /**
     * MD5(key + siteId + mtgKey + userID + userType+Timestamp)
     * 其中:
     * key ：为与系统约定的加密KEY
     * siteId：企业（站点）标识
     * mtgKey：课堂的唯一标识
     * userID：用户 ID 号
     * userType：用户类型
     * timestamp：时间戳
     * 只有第一个不在参数中显示
     */
//    @NonNull
    private String authId;
    private Integer type;

}
