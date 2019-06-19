package com.sinatay.guava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import sun.nio.cs.CharsetMapping;

public class TestMain {
	
	public static void main(String[] args) {
		
		Optional<Integer> possible = Optional.of(5);
		
		possible.isPresent();	// true
		possible.get();			// 5
		//-------------------------------------------------------------
		// 普通Collection的创建
		List<String> list = Lists.newArrayList();
		Set<String> set = Sets.newHashSet();
		Map<String, String> map = Maps.newHashMap();
		
		//不变Collection创建
		ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
		ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
		ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");
		ImmutableList<String> immutableList = ImmutableList.of("1", "2", "3", "4");
		
		//-------------------------------------------------------------
		Map<String, List<Integer>> map1 = new HashMap<String, List<Integer>>();
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		map1.put("aa", list1);
		System.out.println(map1.get("aa"));
		
		Multimap<String, Integer> map2 = ArrayListMultimap.create();
		map2.put("aa", 1);
		map2.put("aa", 2);
		System.out.println(map2.get("aa"));
		
		//-------------------------------------------------------------
		// use java 
		List<String> lists = new ArrayList<String>();
		lists.add("aa");
		lists.add("bb");
		lists.add("cc");
		String str = "";
		for (int i = 0; i < lists.size(); i++) {
			str += "-" + lists.get(i);
		}
		System.out.println(str);
		
		// use guava
		List<String> listStr = new ArrayList<String>();
		listStr.add("aa");
		listStr.add("bb");
		listStr.add("cc");
		String result = Joiner.on("-").join(listStr);
		System.out.println(result);
		
		Map<String, Integer> mapStr = Maps.newHashMap();
		mapStr.put("xiaoming", 12);
		mapStr.put("xiaohong", 13);
		String resultStr = Joiner.on(",").withKeyValueSeparator("=").join(mapStr);
		System.out.println(resultStr);
		
		//use java
		List<String> listS = new ArrayList<String>();
		String a = "1-2-3-4-5-6";
		String[] strs = a.split("-");
		for(int i=0; i<strs.length; i++){
			listS.add(strs[i]);
		}
		System.out.println(listS);
		
		// use guava
		String a1 = "1-2-3-4-5-6";
		List<String> ssList = Splitter.on("-").splitToList(a1);
		System.out.println(ssList);
		
		String str1 = "1-2-3-4- 5- 6 ";
		List<String> strSpac = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str1);
		System.out.println(strSpac);
		
		String strMap = "xiaoming=12,xiaohong=23";
		Map<String, String> mapS = Splitter.on(",").withKeyValueSeparator("=").split(strMap);
		System.out.println(mapS);
		
		String input = "aa.dd,,ff,,.";
		List<String> result2 = Splitter.onPattern("[.|,]").omitEmptyStrings().splitToList(input);
		System.out.println(result2);
		
		// -------------------------------------------------------
		boolean resul = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).matches('K');
		System.out.println(resul);
		String s1 = CharMatcher.inRange('0', '9').retainFrom("abc 123 def");
		System.out.println(s1);
		String s2 = CharMatcher.inRange('0', '9').removeFrom("abc 123 efg");
		System.out.println(s2);
		
		// --------------------------------------------------------
		// 集合的过滤
		ImmutableList<String> names = ImmutableList.of("begin", "code", "Guava", "Java");
		Iterable<String> filtered = Iterables.filter(names, Predicates.or(Predicates.equalTo("Guava"), Predicates.equalTo("Java")));
		System.out.println(filtered);
		// 自定义过滤条件 使用自定义回调方法对Map的每个Value进行操作
		ImmutableMap<String, Integer> m = ImmutableMap.of("xiaoming", 12, "xiaohong", 23);
		Map<String, Integer> m2 = Maps.transformValues(m, new Function<Integer, Integer>() {

			@Override
			public @Nullable Integer apply(@Nullable Integer input) {
				if( input > 12 ) 
					return input;
				else
					return input + 1;
			}
		});
		System.out.println(m2);
	}
}
