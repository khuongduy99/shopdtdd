package com.spring.dto;

import java.util.List;

public class GenericDTO {
	int draw, recordsTotal, recordsFiltered;
	
	List<CategoryDTO> data;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<CategoryDTO> getData() {
		return data;
	}

	public void setData(List<CategoryDTO> data) {
		this.data = data;
	}
	
	
	
}
