package com.smartflow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="core.RecipeItem")
public class RecipeItem implements Serializable {
	private Integer Id;
	//private Integer RecipeId;
	private Recipe recipe;
	private Integer ParameterTypeId;
	private String ParameterName;
	private Double StandardValue;
	private Double USL;
	private Double LSL;
	private String MatchString;
	private Integer CreatorId;
	private Date CreateDateTime;
	private Integer EditorId;
	private Date EditDateTime;
	@Id
	@GeneratedValue
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="RecipeId")
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public Integer getParameterTypeId() {
		return ParameterTypeId;
	}
	public void setParameterTypeId(Integer parameterTypeId) {
		ParameterTypeId = parameterTypeId;
	}
	public String getParameterName() {
		return ParameterName;
	}
	public void setParameterName(String parameterName) {
		ParameterName = parameterName;
	}
	public Double getStandardValue() {
		return StandardValue;
	}
	public void setStandardValue(Double standardValue) {
		StandardValue = standardValue;
	}
	public Double getUSL() {
		return USL;
	}
	public void setUSL(Double uSL) {
		USL = uSL;
	}
	public Double getLSL() {
		return LSL;
	}
	public void setLSL(Double lSL) {
		LSL = lSL;
	}
	public String getMatchString() {
		return MatchString;
	}
	public void setMatchString(String matchString) {
		MatchString = matchString;
	}
	public Integer getCreatorId() {
		return CreatorId;
	}
	public void setCreatorId(Integer creatorId) {
		CreatorId = creatorId;
	}
	public Date getCreateDateTime() {
		return CreateDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		CreateDateTime = createDateTime;
	}
	public Integer getEditorId() {
		return EditorId;
	}
	public void setEditorId(Integer editorId) {
		EditorId = editorId;
	}
	public Date getEditDateTime() {
		return EditDateTime;
	}
	public void setEditDateTime(Date editDateTime) {
		EditDateTime = editDateTime;
	}
	
	
}
