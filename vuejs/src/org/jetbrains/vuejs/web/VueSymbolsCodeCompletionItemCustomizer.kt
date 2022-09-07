// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.jetbrains.vuejs.web

import com.intellij.javascript.web.symbols.*
import com.intellij.javascript.web.symbols.WebSymbolCodeCompletionItemCustomizer.Companion.decorateWithSymbolType

class VueSymbolsCodeCompletionItemCustomizer : WebSymbolCodeCompletionItemCustomizer {
  override fun customize(item: WebSymbolCodeCompletionItem,
                         framework: FrameworkId?, namespace: WebSymbolsContainer.Namespace, kind: SymbolKind): WebSymbolCodeCompletionItem =
    if (namespace == WebSymbolsContainer.Namespace.HTML && framework == VueFramework.ID)
      when (kind) {
        WebSymbol.KIND_HTML_ATTRIBUTES ->
          item.symbol
            ?.takeIf { it.kind == VueWebSymbolsAdditionalContextProvider.KIND_VUE_COMPONENT_PROPS || it.kind == WebSymbol.KIND_JS_EVENTS }
            ?.let { item.decorateWithSymbolType(it) }
          ?: item
        else -> item
      }
    else item
}