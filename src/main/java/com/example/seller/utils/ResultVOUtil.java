package com.example.seller.utils;

import com.example.seller.vo.ResultVO;

/**
 * 暂时没有用这个方法,这样做似乎泛型 就失去了意义 ,和object一样了。
 * @author Administrator
 *
 */
public class ResultVOUtil {
	public static ResultVO success(Object data){
		ResultVO<Object> resultVO = new ResultVO<>();
		resultVO.setCode(0);
		resultVO.setMsg("成功");
		resultVO.setData(data);
		return resultVO;
	}
	public static ResultVO success(){
		return success(null);
	}
	public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
