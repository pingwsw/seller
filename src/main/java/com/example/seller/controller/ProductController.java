package com.example.seller.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seller.entity.ProductCategory;
import com.example.seller.entity.ProductInfo;
import com.example.seller.service.ProductCategoryService;
import com.example.seller.service.ProductInfoService;
import com.example.seller.vo.BuyerProductDataVO;
import com.example.seller.vo.FoodVO;
import com.example.seller.vo.ResultVO;

@RestController
@RequestMapping("/buyer/product")
public class ProductController {
	@Autowired
	ProductInfoService productInfoService;
	@Autowired
	ProductCategoryService productCategoryService;
	@RequestMapping("/list")
	public ResultVO<List<BuyerProductDataVO>> list(){
		ResultVO<List<BuyerProductDataVO>> resultVO = new ResultVO<List<BuyerProductDataVO>>();
		List<ProductInfo> productInfoList = productInfoService.findUpAll();
		HashMap<Integer, ArrayList<FoodVO>> tempMap = new HashMap<Integer,ArrayList<FoodVO>>();
		ArrayList<BuyerProductDataVO> dataList = new ArrayList<BuyerProductDataVO>();
		for (ProductInfo productInfo : productInfoList) {
			Integer categoryType = productInfo.getCategoryType();
			if(!tempMap.containsKey(categoryType)){
				tempMap.put(categoryType, new ArrayList<FoodVO>());
			}
			ArrayList<FoodVO> foodList = tempMap.get(categoryType);
			FoodVO foodVO = new FoodVO();
			BeanUtils.copyProperties(productInfo, foodVO);
			foodList.add(foodVO);
		}
		Set<Integer> keySet = tempMap.keySet();
		Integer[] categoryTypeList = keySet.toArray(new Integer[keySet.size()]);
		List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(Arrays.asList(categoryTypeList));
		for (ProductCategory productCategory : productCategoryList) {
			BuyerProductDataVO buyerProductDataVO = new BuyerProductDataVO();
			BeanUtils.copyProperties(productCategory, buyerProductDataVO);
			buyerProductDataVO.setFoods(tempMap.get(productCategory.getCategoryType()));
			dataList.add(buyerProductDataVO);
		}
		resultVO.setCode(0);
		resultVO.setMsg("成功");
		resultVO.setData(dataList);
		return resultVO;
	}
}
