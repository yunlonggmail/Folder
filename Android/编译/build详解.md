# 1.关于多渠道打包

##1.1	输出对应渠道包
	
	android{
		...
		buildTypes{
			...
			release{
				...
				applicationVariants.all { variant ->
                variant.outputs.each { output ->
                    def outputFile = output.outputFile
                    if (outputFile != null && outputFile.name.endsWith('.apk')) {
                        // 输出apk名称为yuguo_v1.0_wandoujia.apk
                        def fileName = "yuguo_v${defaultConfig.versionName}_${variant.productFlavors[0].name}.apk"
                        output.outputFile = new File(outputFile.parent, fileName)
                    	}
                	}
            	}
			}
		}
	}

##1.2配置渠道包信息
	
其中可以自定义变量，根据变量值进行打包。

其中	manifestPlaceholders表示对应AndroidManifest.xml文件中使用的变量。

在AndroidManifest.xml中使用${channel_value}，来表示对应包的对应值


	// 友盟多渠道打包，渠道flavors
    productFlavors {
        boolean isProductionEnvironment = true;
        _91baidu {
            manifestPlaceholders = [channel_value: "91baidu", easemob_appkey_value: isProductionEnvironment ? "yuguo#yuguo-prod" : "yuguo#yuguo-dev"]
        }
     }

	productFlavors.all { flavor ->
        flavor.manifestPlaceholders = [channel_value: name, easemob_appkey_value: name]
    }