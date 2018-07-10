package com.core.reload;

public abstract interface IReloadSupport {

	public abstract void reload() throws Exception;

	public abstract String getCacheName();

	public abstract String getModuleName();

}
