/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.intellij.terraform.hcl.psi.impl

import com.intellij.openapi.util.TextRange
import org.intellij.terraform.hcl.psi.HCLElementGenerator
import org.intellij.terraform.config.psi.TerraformElementGenerator

class TerraformStringLiteralTextEscaperTestImpl : HCLStringLiteralTextEscaperTest() {
  override fun createElementGenerator(): HCLElementGenerator {
    return TerraformElementGenerator(getProject())
  }

  fun testRelevantRangeTF() {
    doTestRelevantRange("\"\${}\"", TextRange.from(1, 3))
  }

  fun testDecodeSuccessfullyTF() {
    doTestDecode("\"\${}\"", TextRange.from(1, 3), "\${}", 0, 1, 2, 3)
    doTestDecode("\"\${\"\"}\"", TextRange.from(1, 5), "\${\"\"}", 0, 1, 2, 3, 4, 5)
    doTestDecode("\"\${\\\"}\"", TextRange.from(1, 5), "\${\\\"}", 0, 1, 2, 3, 4, 5) // "${\"}" -> ${\"}
    doTestDecode("\"\${\\\\}\"", TextRange.from(1, 5), "\${\\\\}", 0, 1, 2, 3, 4, 5) // "${\\}" -> ${\\}
    doTestDecode("\"\${\\\\\"}\"", TextRange.from(1, 6), "\${\\\\\"}", 0, 1, 2, 3, 4, 5, 6) // "${\\"}" -> ${\\"}
    doTestDecode("\"\${\"\\\\\",\\\\\"\"}\"", TextRange.from(1, 12), "\${\"\\\\\",\\\\\"\"}", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12) // "${"\\",\\""}"  -> ${"\\",\\""}
  }

  fun testOffsetInHostTF() {
    doTestOffsetInHost("\"\${}\"", 1, 2, 3, 4)
    doTestOffsetInHost("\"\${\"\"}\"", 1, 2, 3, 4, 5, 6) // "${""}" -> ${""}
    doTestOffsetInHost("\"\${\\\"}\"", 1, 2, 3, 4, 5, 6) // "${\"}" -> ${\"}
    doTestOffsetInHost("\"\${\\\\}\"", 1, 2, 3, 4, 5, 6) // "${\\}" -> ${\\}
    doTestOffsetInHost("\"\${\"\\\\\",\\\\\"\"}\"", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13) // "${"\\",\\""}" -> ${"\\",\\""}
  }
}