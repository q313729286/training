/*
 * Copyright (C) 2011 Baidu Inc. All rights reserved.
 */
package seker.training.net.requester;

import java.util.List;

/**
 * 网络HTTP请求结果的处理接口
 * 
 * 在发起HTTP请求时，必须要传入一个该接口的实现。 各个不同的HTTP请求的返回，应该对应各自的Response
 * Handler，这样将网络请求和返回结果的处理解耦合。
 * 
 * @see com.baidu.searchbox.net.common.HttpRequester<R>.requestAsync()
 * @author liuxinjian
 * @since 2012-7-20
 * 
 * @param <R>
 *            处理的返回结果Model类型
 */
public interface IResponseHandler<R> {
    
    /** HttpStatus: No Network */
    int NO_NETWORK = -1;
    
    /**
     * 网络HTTP请求结果的监听回调
     * 
     * @see com.baidu.searchbox.net.common.HttpRequester<R>.requestAsync()
     * @author liuxinjian
     * @since 2013-4-26
     * 
     * @param <R>
     *            处理的返回结果Model类型
     */
    public interface IResponseCallback<R> {
        /**
         * 监听到的结果是：网络异常
         * 
         * @param status HTTP请求状态妈：如果无网络，该值为{@link NO_NETWORK}
         */
        void handleNetException(int status);
        
        /**
         * 监听到的结果是：网络状态正常（HttpStatus.SC_OK），但结果为null
         * 可能原始返回值为null，也可能是因为返回内容解析失败。
         * 
         * @param status 
         *              HTTP请求状态妈：如果无网络，该值为{@link NO_NETWORK}
         * @param headers
         *              HTTP返回头信息
         */
        void handleNoResponse(int status, List<ParamPair<String>> headers);
        
        /**
         * 监听到的结果是：有返回值
         * 
         * @param status 
         *              HTTP请求状态妈：如果无网络，该值为{@link NO_NETWORK}
         * @param headers
         *              HTTP返回头信息
         * @param r     返回结果
         */
        void handleResponse(int status, List<ParamPair<String>> headers, R r);
    }

    /**
     * 
     * 网络请求结果处理回调函数，这个回调将在UI线程中被调用，它返回了结果状态和。
     * 
     * @param info
     *            HTTP请求信息，如：url、http请求类型、超时时长等
     * @param status
     *            返回结果状态码：如果无网络，该值为{@link NO_NETWORK}
     * @param headers
     *            HTTP返回头信息
     * @param response
     *            返回结果
     */
    void onResult(HttpRequestInfo info, int status, List<ParamPair<String>> headers, R response);
}
