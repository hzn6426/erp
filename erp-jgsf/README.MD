jackjson序列化标志：
<1>.@JsonIgnoreType-用于类上:排除整个类中所有的属性
<2>.@JsonIgnoreProperties-用于属性上:排除类中的某个属性
<3>.@JsonInclude(Include.NON_NULL)-用于类上:排除null字段
swagger标志：
<1>.@ApiModel-用于类上:对于类的文档注释
<2>.@ApiModelProperty-用于属性上:对于属性的注释，@ApiModelProperty(hidden=true),文档中隐藏该属性
