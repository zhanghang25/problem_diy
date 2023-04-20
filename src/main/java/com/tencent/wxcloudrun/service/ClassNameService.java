package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.ClassName;

import java.util.Optional;

public interface ClassNameService {

  Optional<ClassName> getClassName(Integer id);

  void upsertClassName(ClassName classsName);

  void clearClassName(Integer id);
}
