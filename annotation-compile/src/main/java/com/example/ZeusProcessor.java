package com.example;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

public class ZeusProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        /*获取所有被PrintData注解的元素*/
        for(int i=0;i<10;i++) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "\twl,hello!: -----------------");
        }
        /*返回true意味着这个注解在这里被处理，其他的注解处理器不会再处理该注解*/
        return true;
    }

    /*支持的注解，可以使用通配符*/
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<String>();
        set.add("com.example.PrintData");
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
