package com.qaoption.mapper;

import java.util.List;

import com.qaoption.model.OptionContract;
@org.apache.ibatis.annotations.Mapper
public interface ContractMapper { 
    
    public List<OptionContract> findOptionContractByUserId(int userId);
    public List<OptionContract> findFutureContractByUserId(int userId);
    public void addContract(OptionContract c);
}
