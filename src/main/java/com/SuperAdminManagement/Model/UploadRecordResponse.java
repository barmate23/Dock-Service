package com.SuperAdminManagement.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UploadRecordResponse<T,E>{
    List<T> validRecords;
    List<E> errorRecords;
}
