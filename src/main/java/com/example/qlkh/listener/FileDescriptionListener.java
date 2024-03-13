package com.example.qlkh.listener;

import com.example.qlkh.entity.FileDescription;
import com.example.qlkh.utils.EbsFileUtils;

import javax.persistence.PreRemove;

public class FileDescriptionListener {

    @PreRemove
    private void remove(FileDescription fileDescription) {
        EbsFileUtils.tickFileToDelete(fileDescription);
    }
}
