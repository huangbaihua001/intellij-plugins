// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.terraform.config

import com.intellij.openapi.options.advanced.AdvancedSettings

object Constants {
  const val HAS_DYNAMIC_ATTRIBUTES: String = "__has_dynamic_attributes"
  const val TIMEOUTS: String = "__timeouts__"

  internal const val HCL_RESOURCE_IDENTIFIER: String = "resource"
  internal const val HCL_DATASOURCE_IDENTIFIER: String = "data"
  internal const val HCL_PROVIDER_IDENTIFIER: String = "provider"
  internal const val HCL_MODULE_IDENTIFIER: String = "module"
  internal const val HCL_VARIABLE_IDENTIFIER: String = "variable"
  internal const val HCL_OUTPUT_IDENTIFIER: String = "output"
  internal const val HCL_TERRAFORM_IDENTIFIER: String = "terraform"
  internal const val HCL_LOCALS_IDENTIFIER: String = "locals"
  internal const val HCL_PROVISIONER_IDENTIFIER: String = "provisioner"
  internal const val HCL_BACKEND_IDENTIFIER: String = "backend"

  internal const val TERRAFORM_DOMAIN: String = "terraform.io"
  internal const val REGISTRY_DOMAIN: String = "registry.terraform.io"
  internal const val LATEST_VERSION: String = "latest"

  internal val OFFICIAL_PROVIDERS_NAMESPACES = setOf("hashicorp")

  internal val shouldDownloadDocs: Boolean
    get() = AdvancedSettings.getBoolean("org.intellij.terraform.config.documentation.download")

}