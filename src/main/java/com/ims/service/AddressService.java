package com.ims.service;

import java.util.List;

import com.ims.domain.Address;

public interface AddressService
{
	public void addAddress(Address address);
	public void removeAddressById(Long id);
	public void removeAddressByAddress(String address);
	public Address getAddressById(Long id);
	public Address getAddressByAddress(String address);
	public List<Address> getAllAddresses();
}
