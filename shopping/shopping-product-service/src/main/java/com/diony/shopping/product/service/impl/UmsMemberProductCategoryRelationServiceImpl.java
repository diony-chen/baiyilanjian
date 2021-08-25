package com.diony.shopping.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diony.shopping.user.entity.UmsMemberProductCategoryRelation;
import com.diony.shopping.user.mapper.UmsMemberProductCategoryRelationMapper;
import com.diony.shopping.user.service.UmsMemberProductCategoryRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类） 服务实现类
 * </p>
 *
 * @author diony_chen
 * @since 2021-04-01
 */
@Service
public class UmsMemberProductCategoryRelationServiceImpl extends ServiceImpl<UmsMemberProductCategoryRelationMapper, UmsMemberProductCategoryRelation> implements UmsMemberProductCategoryRelationService {

}
