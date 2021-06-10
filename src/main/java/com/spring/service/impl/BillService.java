package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.BillDTO;
import com.spring.dto.BillDetailDTO;
import com.spring.entity.BillDetailEntity;
import com.spring.entity.BillEntity;
import com.spring.model.MessageAlertModel;
import com.spring.repository.BillDetailRepository;
import com.spring.repository.BillRepository;
import com.spring.service.IBillService;
import com.spring.service.IProductService;
import com.spring.utils.StatusCustom;
import com.spring.utils.WebUtils;

@Service
public class BillService implements IBillService{
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private BillDetailRepository billDetailRepository;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional
	public MessageAlertModel save(BillDTO dto) {
		String alert = "", message = "";
		List<BillDetailEntity> listBillDetail = new ArrayList<BillDetailEntity>();
		for(BillDetailDTO bill : dto.getListBillDetail()) {
			productService.updateQtyProduct(bill.getIdProduct(), bill.getQty());
			listBillDetail.add(modelMapper.map(bill, BillDetailEntity.class));
		}
		BillEntity entity = modelMapper.map(dto, BillEntity.class);
		entity.setStatus(StatusCustom.CONFIRMED);
		entity.setListBillDetail(listBillDetail);
		entity.setUserId(dto.getUserId());
		entity.setPayment(dto.isPayment());
		entity.setCode(dto.getCode());
		entity.setPhone(dto.getPhone());
		try {
			billRepository.save(entity);
			alert = "success";
			message = "Thêm Thành Công";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		
		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public MessageAlertModel update(BillDTO dto) {
		BillEntity entity = billRepository.findOne(dto.getId());
		if(entity == null) return new MessageAlertModel("danger", "Đơn hàng không tồn tại", new Date());
		
		entity.setStatus(dto.getStatus());
		try {
			billRepository.save(entity);
			return new MessageAlertModel("success", "Cập nhật trạng thái thành công", new Date());
		} catch (Exception e) {
			return new MessageAlertModel("danger", WebUtils.getMessageWhenErrorEntity(e.getMessage()), new Date());
		}
	}

	@Override
	public MessageAlertModel delete(Long[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageAlertModel order(BillDTO dto) {
		
		return null;
	}

	@Override
	public List<BillDTO> findAllByUserIdAndStatus(Long userId, String status) {
		List<BillDTO> result = new ArrayList<BillDTO>();
		List<BillEntity> entities = billRepository.findAllByUserIdAndStatus(userId, status);
		if(entities.size() == 0) return null;
		for (BillEntity item :  entities) {
			BillDTO dto = modelMapper.map(item, BillDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<BillDTO> findAllByUserId(Long userId) {
		List<BillDTO> result = new ArrayList<BillDTO>();
		List<BillEntity> entities = billRepository.findAllByUserId(userId);
		if(entities.size() == 0) return null;
		for (BillEntity item :  entities) {
			BillDTO dto = modelMapper.map(item, BillDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<BillDTO> findAllByStatus(String status) {
		List<BillDTO> result = new ArrayList<BillDTO>();
		List<BillEntity> entities = billRepository.findAllByStatus(status);
		for (BillEntity item :  entities) {
			BillDTO dto = modelMapper.map(item, BillDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public BillDTO findOneById(Long id) {
		BillEntity entity = billRepository.findOne(id);
		if (entity == null)
			return null;
		return modelMapper.map(entity, BillDTO.class);
	}


	@Override
	public int sumMoneyByYearAndMonth(int year, int month) {
		return billRepository.sumMoneyByYearAndMonth(year, month);
	}

	@Override
	public int sumMoneyByYearAndMonthAndDay(int year, int month, int day) {
		return billRepository.sumMoneyByYearAndMonthAndDay(year, month, day);
	}

	@Override
	public int countByYearAndMonthAndStatus(int year, int month, String status) {
		return billRepository.countByYearAndMonthAndStatus(year, month, status);
	}

	@Override
	public int countByYearAndMonthAndDayAndStatus(int year, int month, int day, String status) {
		return billRepository.countByYearAndMonthAndDayAndStatus(year, month, day, status);
	}

	@Override
	public int countByYearAndStatus(int year, String status) {
		return billRepository.countByYearAndStatus(year, status);
	}

	@Override
	public BillDTO findOneByCode(String code) {
		BillDTO result = modelMapper.map(billRepository.findOneByCode(code), BillDTO.class);
		List<BillDetailDTO> billDetail = new ArrayList<BillDetailDTO>();
		List<BillDetailEntity> entities = billDetailRepository.findAllByBillId(result.getId());
		for (BillDetailEntity item :  entities) {
			BillDetailDTO dto = modelMapper.map(item, BillDetailDTO.class);
			billDetail.add(dto);
		}
		result.setListBillDetail(billDetail);
		return result;
	}

}
