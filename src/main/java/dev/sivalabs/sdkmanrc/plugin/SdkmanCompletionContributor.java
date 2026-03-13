package dev.sivalabs.sdkmanrc.plugin;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;

final class SdkmanCompletionContributor extends CompletionContributor {

  SdkmanCompletionContributor() {
    extend(CompletionType.BASIC,
            PlatformPatterns.psiElement(),
            new SdkmanCompletionProvider()
    );
  }

}
