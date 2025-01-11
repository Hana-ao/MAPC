package org.example.td5.q2.metier.impl;

import org.example.td5.q2.metier.api.FileSystemElement;

import java.util.ArrayList;
import java.util.List;

public class SimpleFile implements FileSystemElement { //Leaf

	private FileId fid;
	private String name;
	private UserId owner;
	private int size;
	private FileId parent;

	public SimpleFile(FileId fid, String name, UserId owner, int size, FileId parent) {
		this.fid = fid;
		this.name = name;
		this.owner = owner;
		this.size = size;
		this.parent = parent;
	}

	@Override
	public FileId getFid() {
		return fid;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public UserId getOwner() {
		return owner;
	}

	@Override
	public List<FileSystemElement> getContents() {
		return new ArrayList<>();
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public FileId getParent() {
		return parent;
	}
}
