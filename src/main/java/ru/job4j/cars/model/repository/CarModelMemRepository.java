package ru.job4j.cars.model.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarMarc;
import ru.job4j.cars.model.CarModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class CarModelMemRepository implements  CarModelAbstractRepository {
    private static final List<String> BODY_NAMES = List.of(
            "седан",
            "хэтчбек 3 дверей",
            "хэтчбек 5 дверей",
            "лифтбек",
            "внедорожник 3 двери",
            "внедорожник 5 двери",
            "универсал",
            "купе",
            "минивен",
            "пикап",
            "лимузин",
            "фургон",
            "кабриолет"
    );
    private final List<String> audiModels = List.of(
            "100",  "седан",
            "200",  "седан",
            "50",  "седан",
            "80",  "седан",
            "90",  "седан",
            "920",  "седан",
            "A1",  "седан",
            "A2",  "седан",
            "A3",  "седан",
            "A4",  "седан",
            "A4 allroad",  "седан",
            "A5",  "седан",
            "A6",  "седан",
            "A6 allroad",  "седан",
            "A7",  "седан",
            "A8",  "седан",
            "Cabriolet",  "седан",
            "Coupe",  "седан",
            "e-tron",  "седан",
            "e-tron GT",  "седан",
            "e-tron S",  "седан",
            "e-tron S Sportback",  "седан",
            "e-tron Sportback",  "седан",
            "NSU RO 80",  "седан",
            "Q2",  "седан",
            "Q2L",  "седан",
            "Q3",  "седан",
            "Q3 Sportback",  "седан",
            "Q4 e-tron",  "седан",
            "Q4 Sportback e-tron",  "седан",
            "Q5",  "седан",
            "Q5 e-tron",  "седан",
            "Q5 Sportback",  "седан",
            "Q5L",  "седан",
            "Q6",  "седан",
            "Q7",  "седан",
            "Q8",  "седан",
            "Q8 e-tron",  "седан",
            "Q8 Sportback e-tron",  "седан",
            "Quattro",  "седан",
            "R8",  "седан",
            "R8 LMP",  "седан",
            "RS 2",  "седан",
            "RS 3",  "седан",
            "RS 4",  "седан",
            "RS 5",  "седан",
            "RS 6",  "седан",
            "RS 7",  "седан",
            "RS e-tron GT",  "седан",
            "RS Q3",  "седан",
            "RS Q3 Sportback",  "седан",
            "RS Q8",  "седан",
            "S1",  "седан",
            "S2",  "седан",
            "S3",  "седан",
            "S4",  "седан",
            "S5",  "седан",
            "S6",  "седан",
            "S7",  "седан",
            "S8",  "седан",
            "SQ2",  "седан",
            "SQ5",  "седан",
            "SQ5 Sportback",  "седан",
            "SQ7",  "седан",
            "SQ8",  "седан",
            "SQ8 e-tron",  "седан",
            "SQ8 Sportback e-tron",  "седан",
            "TT",  "седан",
            "TT RS",  "седан",
            "TTS",  "седан",
            "Typ R",  "седан",
            "V8",  "седан"
    );
    private final List<String> mersedesModels = List.of(
            "190 (W201)", "седан",
            "190 SL", "седан",
            "220 (W187)", "седан",
            "A-Класс", "седан",
            "A-Класс AMG", "седан",
            "AMG GT", "седан",
            "AMG ONE", "седан",
            "B-Класс", "седан",
            "C-Класс", "седан",
            "C-Класс AMG", "седан",
            "Citan", "седан",
            "CL-Класс", "седан",
            "CL-Класс AMG", "седан",
            "CLA", "седан",
            "CLA AMG", "седан",
            "CLC-Класс", "седан",
            "CLK AMG GTR", "седан",
            "CLK-Класс", "седан",
            "CLK-Класс AMG", "седан",
            "CLS", "седан",
            "CLS AMG", "седан",
            "E-Класс", "седан",
            "E-Класс AMG", "седан",
            "EQA", "седан",
            "EQB", "седан",
            "EQC", "седан",
            "EQE", "седан",
            "EQE AMG", "седан",
            "EQE SUV", "седан",
            "EQE SUV AMG", "седан",
            "EQS", "седан",
            "EQS AMG", "седан",
            "EQS SUV", "седан",
            "EQV", "седан",
            "G-Класс", "седан",
            "G-Класс AMG", "седан",
            "G-Класс AMG 6x6", "седан",
            "GL-Класс", "седан",
            "GL-Класс AMG", "седан",
            "GLA", "седан",
            "GLA AMG", "седан",
            "GLB", "седан",
            "GLB AMG", "седан",
            "GLC", "седан",
            "GLC AMG", "седан",
            "GLC Coupe", "седан",
            "GLC Coupe AMG", "седан",
            "GLE", "седан",
            "GLE AMG", "седан",
            "GLE Coupe", "седан",
            "GLE Coupe AMG", "седан",
            "GLK-Класс", "седан",
            "GLS", "седан",
            "GLS AMG", "седан",
            "M-Класс", "седан",
            "M-Класс AMG", "седан",
            "Marco Polo", "седан",
            "Maybach G 650 Landaulet", "седан",
            "Maybach GLS", "седан",
            "Maybach S-Класс", "седан",
            "Metris", "седан",
            "R-Класс", "седан",
            "R-Класс AMG", "седан",
            "S-Класс", "седан",
            "S-Класс AMG", "седан",
            "Simplex", "седан",
            "SL-Класс", "седан",
            "SL-Класс AMG", "седан",
            "SLC", "седан",
            "SLC AMG", "седан",
            "SLK-Класс", "седан",
            "SLK-Класс AMG", "седан",
            "SLR McLaren", "седан",
            "SLS AMG", "седан",
            "T-Класс", "седан",
            "V-Класс", "седан",
            "Vaneo", "седан",
            "Viano", "седан",
            "Vito", "седан",
            "W100", "седан",
            "W105", "седан",
            "W108", "седан",
            "W110", "седан",
            "W111", "седан",
            "W114", "седан",
            "W115", "седан",
            "W120", "седан",
            "W121", "седан",
            "W123", "седан",
            "W124", "седан",
            "W128", "седан",
            "W136", "седан",
            "W138", "седан",
            "W142", "седан",
            "W180", "седан",
            "W186", "седан",
            "W188", "седан",
            "W189", "седан",
            "W191", "седан",
            "W21", "седан",
            "W29", "седан",
            "X-Класс", "седан"
    );
    private final List<String> hyundaiModels = List.of(
            "Accent", "седан",
            "Aslan", "седан",
            "Atos", "седан",
            "Avante", "седан",
            "Avante N", "седан",
            "Azera", "седан",
            "Bayon", "седан",
            "Casper", "седан",
            "Celesta", "седан",
            "Centennial", "седан",
            "Click", "седан",
            "Coupe", "седан",
            "Creta", "седан",
            "Custo", "седан",
            "Dynasty", "седан",
            "Elantra", "седан",
            "Elantra N", "седан",
            "Encino", "седан",
            "Entourage", "седан",
            "EON", "седан",
            "Equus", "седан",
            "Excel", "седан",
            "Galloper", "седан",
            "Genesis", "седан",
            "Genesis Coupe", "седан",
            "Getz", "седан",
            "Grace", "седан",
            "Grand Starex", "седан",
            "Grandeur", "седан",
            "H-1", "седан",
            "H200", "седан",
            "HB20", "седан",
            "i10", "седан",
            "i20", "седан",
            "i20 N", "седан",
            "i30", "седан",
            "i30 N", "седан",
            "i40", "седан",
            "IONIQ", "седан",
            "IONIQ 5", "седан",
            "IONIQ 6", "седан",
            "ix20", "седан",
            "ix35", "седан",
            "ix55", "седан",
            "Kona", "седан",
            "Kona N", "седан",
            "Lantra", "седан",
            "Lavita", "седан",
            "Marcia", "седан",
            "Matrix", "седан",
            "Maxcruz", "седан",
            "Mistra", "седан",
            "Palisade", "седан",
            "Pony", "седан",
            "Santa Cruz", "седан",
            "Santa Fe", "седан",
            "Santamo", "седан",
            "Scoupe", "седан",
            "Solaris", "седан",
            "Sonata", "седан",
            "Starex", "седан",
            "Staria", "седан",
            "Stellar", "седан",
            "Terracan", "седан",
            "Tiburon", "седан",
            "Trajet", "седан",
            "Tucson", "седан",
            "Tuscani", "седан",
            "Veloster", "седан",
            "Venue", "седан",
            "Veracruz", "седан",
            "Verna", "седан",
            "XG", "седан"
            );
    private final List<String> renaultModels = List.of(
             "10", "седан",
             "11", "седан",
             "12", "седан",
             "14", "седан",
             "15", "седан",
             "16", "седан",
             "17", "седан",
             "18", "седан",
             "19", "седан",
             "20", "седан",
             "21", "седан",
             "25", "седан",
             "30", "седан",
             "4", "седан",
             "4CV", "седан",
             "5", "седан",
             "6", "седан",
             "8", "седан",
             "9", "седан",
             "Alaskan", "седан",
             "Arkana", "седан",
             "Austral", "седан",
             "Avantime", "седан",
             "Captur", "седан",
             "Caravelle", "седан",
             "City K-ZE", "седан",
             "Clio", "седан",
             "Clio RS", "седан",
             "Clio V6", "седан",
             "Dauphine", "седан",
             "Dokker", "седан",
             "Duster", "седан",
             "Espace", "седан",
             "Floride", "седан",
             "Fluence", "седан",
             "Fregate", "седан",
             "Fuego", "седан",
             "Kadjar", "седан",
             "Kangoo", "седан",
             "Kaptur", "седан",
             "Koleos", "седан",
             "KWID", "седан",
             "Laguna", "седан",
             "Latitude", "седан",
             "Lodgy", "седан",
             "Logan", "седан",
             "Megane", "седан",
             "Megane E-Tech", "седан",
             "Megane RS", "седан",
             "Modus", "седан",
             "Rodeo", "седан",
             "Safrane", "седан",
             "Sandero", "седан",
             "Sandero RS", "седан",
             "Scenic", "седан",
             "Sport Spider", "седан",
             "Symbol", "седан",
             "Talisman", "седан",
             "Trafic", "седан",
             "Twingo", "седан",
             "Twizy", "седан",
             "Vel Satis", "седан",
             "Vivastella", "седан",
             "Wind", "седан",
             "ZOE", "седан"
    );
    private final List<String> bmwModels = List.of(
             "02 (E10)", "седан",
             "1 серии", "седан",
             "1M", "седан",
             "2 серии", "седан",
             "2 серии Active Tourer", "седан",
             "2 серии Gran Tourer", "седан",
             "2000 C/CS", "седан",
             "3 серии", "седан",
             "3/15", "седан",
             "315", "седан",
             "3200", "седан",
             "321", "седан",
             "326", "седан",
             "327", "седан",
             "340", "седан",
             "4 серии", "седан",
             "5 серии", "седан",
             "501", "седан",
             "502", "седан",
             "503", "седан",
             "507", "седан",
             "6 серии", "седан",
             "600", "седан",
             "7 серии", "седан",
             "700", "седан",
             "8 серии", "седан",
             "E3", "седан",
             "E9", "седан",
             "i3", "седан",
             "i4", "седан",
             "i7", "седан",
             "i8", "седан",
             "iX", "седан",
             "iX1", "седан",
             "iX3", "седан",
             "M1", "седан",
             "M2", "седан",
             "M3", "седан",
             "M4", "седан",
             "M5", "седан",
             "M6", "седан",
             "M8", "седан",
             "New Class", "седан",
             "X1", "седан",
             "X2", "седан",
             "X3", "седан",
             "X3 M", "седан",
             "X4", "седан",
             "X4 M", "седан",
             "X5", "седан",
             "X5 M", "седан",
             "X6", "седан",
             "X6 M", "седан",
             "X7", "седан",
             "XM", "седан",
             "Z1", "седан",
             "Z3", "седан",
             "Z3 M", "седан",
             "Z4", "седан",
             "Z4 M", "седан",
             "Z8", "седан"
    );
    
    private final List<String> kiaModels = List.of(
            "Avella", "седан",
             "Borrego", "седан",
             "Cadenza", "седан",
             "Capital", "седан",
             "Carens", "седан",
             "Carnival", "седан",
             "Carstar", "седан",
             "Ceed", "седан",
             "Ceed GT", "седан",
             "Cerato", "седан",
             "Clarus", "седан",
             "Concord", "седан",
             "Elan", "седан",
             "Enterprise", "седан",
             "EV6", "седан",
             "Forte", "седан",
             "Joice", "седан",
             "K3", "седан",
             "K5", "седан",
             "K7", "седан",
             "K8", "седан",
             "K9", "седан",
             "K900", "седан",
             "KX3", "седан",
             "Lotze", "седан",
             "Magentis", "седан",
             "Mentor", "седан",
             "Mohave", "седан",
             "Morning", "седан",
             "Niro", "седан",
             "Opirus", "седан",
             "Optima", "седан",
             "Picanto", "седан",
             "Potentia", "седан",
             "Pregio", "седан",
             "Pride", "седан",
             "Proceed", "седан",
             "Quanlima", "седан",
             "Quoris", "седан",
             "Ray", "седан",
             "Retona", "седан",
             "Rio", "седан",
             "Sedona", "седан",
             "Seltos", "седан",
             "Sephia", "седан",
             "Shuma", "седан",
             "Sorento", "седан",
             "Soul", "седан",
             "Soul EV", "седан",
             "Spectra", "седан",
             "Sportage", "седан",
             "Sportage (China)", "седан",
             "Stinger", "седан",
             "Stonic", "седан",
             "Telluride", "седан",
             "Towner", "седан",
             "Venga", "седан",
             "Visto", "седан",
             "X-Trek", "седан",
             "XCeed", "седан"
    );
    private final List<String> mitsubishiModels = List.of(
             "3000 GT", "седан",
             "500", "седан",
             "Airtrek", "седан",
             "Aspire", "седан",
             "ASX", "седан",
             "Attrage", "седан",
             "Bravo", "седан",
             "Carisma", "седан",
             "Celeste", "седан",
             "Challenger", "седан",
             "Chariot", "седан",
             "Colt", "седан",
             "Cordia", "седан",
             "Debonair", "седан",
             "Delica", "седан",
             "Delica D:2", "седан",
             "Delica D:3", "седан",
             "Delica D:5", "седан",
             "Diamante", "седан",
             "Dignity", "седан",
             "Dingo", "седан",
             "Dion", "седан",
             "Eclipse", "седан",
             "Eclipse Cross", "седан",
             "eK Active", "седан",
             "eK Classic", "седан",
             "eK Custom", "седан",
             "eK Space", "седан",
             "eK Sport", "седан",
             "eK Wagon", "седан",
             "Emeraude", "седан",
             "Endeavor", "седан",
             "Eterna", "седан",
             "Freeca", "седан",
             "FTO", "седан",
             "Galant", "седан",
             "Galant Fortis", "седан",
             "Grandis", "седан",
             "GTO", "седан",
             "i", "седан",
             "i-MiEV", "седан",
             "Jeep J", "седан",
             "L200", "седан",
             "L300", "седан",
             "L400", "седан",
             "Lancer", "седан",
             "Lancer Cargo", "седан",
             "Lancer Evolution", "седан",
             "Lancer Ralliart", "седан",
             "Legnum", "седан",
             "Libero", "седан",
             "Minica", "седан",
             "Minicab", "седан",
             "Mirage", "седан",
             "Montero", "седан",
             "Montero Sport", "седан",
             "Outlander", "седан",
             "Outlander Sport", "седан",
             "Pajero", "седан",
             "Pajero iO", "седан",
             "Pajero Junior", "седан",
             "Pajero Mini", "седан",
             "Pajero Pinin", "седан",
             "Pajero Sport", "седан",
             "Pistachio", "седан",
             "Proudia", "седан",
             "Raider", "седан",
             "RVR", "седан",
             "Sapporo", "седан",
             "Savrin", "седан",
             "Sigma", "седан",
             "Space Gear", "седан",
             "Space Runner", "седан",
             "Space Star", "седан",
             "Space Wagon", "седан",
             "Starion", "седан",
             "Strada", "седан",
             "Toppo", "седан",
             "Town Box", "седан",
             "Tredia", "седан",
             "Triton", "седан",
             "Xpander", "седан"
            );
    private final List<String> fordModels = List.of(
             "Aerostar", "седан",
             "Aspire", "седан",
             "B-MAX", "седан",
             "Bronco", "седан",
             "Bronco Sport", "седан",
             "Bronco-II", "седан",
             "C-MAX", "седан",
             "Capri", "седан",
             "Consul", "седан",
             "Contour", "седан",
             "Cortina", "седан",
             "Cougar", "седан",
             "Country Squire", "седан",
             "Crown Victoria", "седан",
             "Custom", "седан",
             "Econoline", "седан",
             "Econovan", "седан",
             "EcoSport", "седан",
             "Edge", "седан",
             "Equator", "седан",
             "Escape", "седан",
             "Escort", "седан",
             "Escort (North America)", "седан",
             "Everest", "седан",
             "Evos", "седан",
             "Excursion", "седан",
             "Expedition", "седан",
             "Explorer", "седан",
             "Explorer Sport Trac", "седан",
             "F-150", "седан",
             "F-2", "седан",
             "Fairlane", "седан",
             "Fairmont", "седан",
             "Falcon", "седан",
             "Festiva", "седан",
             "Fiesta", "седан",
             "Fiesta ST", "седан",
             "Five Hundred", "седан",
             "Flex", "седан",
             "Focus", "седан",
             "Focus RS", "седан",
             "Focus ST", "седан",
             "Freda", "седан",
             "Freestar", "седан",
             "Freestyle", "седан",
             "Fusion", "седан",
             "Fusion (North America)", "седан",
             "Galaxie", "седан",
             "Galaxy", "седан",
             "GPA", "седан",
             "Granada", "седан",
             "Granada (North America)", "седан",
             "GT", "седан",
             "GT40", "седан",
             "Ikon", "седан",
             "Ixion", "седан",
             "KA", "седан",
             "Kuga", "седан",
             "Laser", "седан",
             "LTD Country Squire", "седан",
             "LTD Crown Victoria", "седан",
             "M151", "седан",
             "Mainline", "седан",
             "Maverick", "седан",
             "Model A", "седан",
             "Model T", "седан",
             "Mondeo", "седан",
             "Mondeo ST", "седан",
             "Mustang", "седан",
             "Mustang Mach-E", "седан",
             "Orion", "седан",
             "Probe", "седан",
             "Puma", "седан",
             "Puma ST", "седан",
             "Ranchero", "седан",
             "Ranger", "седан",
             "Ranger (North America)", "седан",
             "S-MAX", "седан",
             "Scorpio", "седан",
             "Sierra", "седан",
             "Spectron", "седан",
             "Taunus", "седан",
             "Taurus", "седан",
             "Taurus X", "седан",
             "Telstar", "седан",
             "Tempo", "седан",
             "Territory", "седан",
             "Thunderbird", "седан",
             "Torino", "седан",
             "Tourneo Connect", "седан",
             "Tourneo Courier", "седан",
             "Tourneo Custom", "седан",
             "Transit Connect", "седан",
             "Transit Custom", "седан",
             "V8", "седан",
             "Windstar", "седан",
             "Zephyr", "седан"
    );
    private final List<String> nissanModels = List.of(
             "100NX", "седан",
             "180SX", "седан",
             "200SX", "седан",
             "240SX", "седан",
             "280ZX", "седан",
             "300ZX", "седан",
             "350Z", "седан",
             "370Z", "седан",
             "AD", "седан",
             "Almera", "седан",
             "Almera Classic", "седан",
             "Almera Tino", "седан",
             "Altima", "седан",
             "Ariya", "седан",
             "Armada", "седан",
             "Auster", "седан",
             "Avenir", "седан",
             "Bassara", "седан",
             "BE-1", "седан",
             "Bluebird", "седан",
             "Bluebird Maxima", "седан",
             "Bluebird Sylphy", "седан",
             "Caravan", "седан",
             "Cedric", "седан",
             "Cefiro", "седан",
             "Cherry", "седан",
             "Cima", "седан",
             "Clipper Rio", "седан",
             "Crew", "седан",
             "Cube", "седан",
             "Datsun", "седан",
             "Dayz", "седан",
             "Dayz Roox", "седан",
             "Dualis", "седан",
             "Elgrand", "седан",
             "Exa", "седан",
             "Expert", "седан",
             "Fairlady Z", "седан",
             "Figaro", "седан",
             "Fuga", "седан",
             "Gloria", "седан",
             "GT-R", "седан",
             "Homy", "седан",
             "Hypermini", "седан",
             "Juke", "седан",
             "Juke Nismo", "седан",
             "Kicks", "седан",
             "Kix", "седан",
             "Lafesta", "седан",
             "Langley", "седан",
             "Largo", "седан",
             "Latio", "седан",
             "Laurel", "седан",
             "Leaf", "седан",
             "Leopard", "седан",
             "Liberta Villa", "седан",
             "Liberty", "седан",
             "Livina", "седан",
             "Lucino", "седан",
             "March", "седан",
             "Maxima", "седан",
             "Micra", "седан",
             "Mistral", "седан",
             "Moco", "седан",
             "Murano", "седан",
             "Navara (Frontier)", "седан",
             "Note", "седан",
             "NP300", "седан",
             "NV100 Clipper", "седан",
             "NV200", "седан",
             "NV300", "седан",
             "NV350 Caravan", "седан",
             "NX Coupe", "седан",
             "Otti", "седан",
             "Pao", "седан",
             "Pathfinder", "седан",
             "Patrol", "седан",
             "Pino", "седан",
             "Pixo", "седан",
             "Prairie", "седан",
             "Presage", "седан",
             "Presea", "седан",
             "President", "седан",
             "Primastar", "седан",
             "Primera", "седан",
             "Pulsar", "седан",
             "Qashqai", "седан",
             "Qashqai+2", "седан",
             "Quest", "седан",
             "R'nessa", "седан",
             "Rasheen", "седан",
             "Rogue", "седан",
             "Rogue Sport", "седан",
             "Roox", "седан",
             "Safari", "седан",
             "Sentra", "седан",
             "Serena", "седан",
             "Silvia", "седан",
             "Skyline", "седан",
             "Skyline Crossover", "седан",
             "Stagea", "седан",
             "Stanza", "седан",
             "Sunny", "седан",
             "Sylphy Zero Emission", "седан",
             "Teana", "седан",
             "Terra", "седан",
             "Terrano", "седан",
             "Terrano Regulus", "седан",
             "Tiida", "седан",
             "Tino", "седан",
             "Titan", "седан",
             "Urvan", "седан",
             "Vanette", "седан",
             "Versa", "седан",
             "Wingroad", "седан",
             "X-Terra", "седан",
             "X-Trail", "седан",
             "Xterra", "седан",
             "Z", "седан"
            );
    private final List<String> subaruModels = List.of(
             "1000", "седан",
             "360", "седан",
             "Alcyone", "седан",
             "Ascent", "седан",
             "Baja", "седан",
             "Bighorn", "седан",
             "Bistro", "седан",
             "Brat", "седан",
             "BRZ", "седан",
             "Crosstrek", "седан",
             "Dex", "седан",
             "Dias Wagon", "седан",
             "Domingo", "седан",
             "Exiga", "седан",
             "Forester", "седан",
             "Impreza", "седан",
             "Impreza WRX", "седан",
             "Impreza WRX STi", "седан",
             "Justy", "седан",
             "Legacy", "седан",
             "Legacy Lancaster", "седан",
             "Leone", "седан",
             "Levorg", "седан",
             "Libero", "седан",
             "Lucra", "седан",
             "Outback", "седан",
             "Pleo", "седан",
             "Pleo Plus", "седан",
             "R1", "седан",
             "R2", "седан",
             "Rex", "седан",
             "Sambar", "седан",
             "Solterra", "седан",
             "Stella", "седан",
             "SVX", "седан",
             "Traviq", "седан",
             "Trezia", "седан",
             "Tribeca", "седан",
             "Vivio", "седан",
             "WRX", "седан",
             "WRX STi", "седан",
             "XT", "седан",
             "XV", "седан"
            );
    private final List<String> opelModels = List.of(
             "Adam", "седан",
             "Admiral", "седан",
             "Agila", "седан",
             "Ampera", "седан",
             "Antara", "седан",
             "Ascona", "седан",
             "Astra", "седан",
             "Astra OPC", "седан",
             "Calibra", "седан",
             "Campo", "седан",
             "Cascada", "седан",
             "Combo", "седан",
             "Commodore", "седан",
             "Corsa", "седан",
             "Corsa OPC", "седан",
             "Crossland X", "седан",
             "Diplomat", "седан",
             "Frontera", "седан",
             "Grandland X", "седан",
             "GT", "седан",
             "Insignia", "седан",
             "Insignia OPC", "седан",
             "Kadett", "седан",
             "Kapitan", "седан",
             "Karl", "седан",
             "Manta", "седан",
             "Meriva", "седан",
             "Meriva OPC", "седан",
             "Mokka", "седан",
             "Monterey", "седан",
             "Monza", "седан",
             "Olympia", "седан",
             "Omega", "седан",
             "P4", "седан",
             "Rekord", "седан",
             "Senator", "седан",
             "Signum", "седан",
             "Sintra", "седан",
             "Speedster", "седан",
             "Super Six", "седан",
             "Tigra", "седан",
             "Vectra", "седан",
             "Vectra OPC", "седан",
             "Vita", "седан",
             "Vivaro", "седан",
             "Zafira", "седан",
             "Zafira Life", "седан",
             "Zafira OPC", "седан"
    );
    private final List<String> toyotaModels = List.of(
             "2000GT", "седан",
             "4Runner", "седан",
             "Allex", "седан",
             "Allion", "седан",
             "Alphard", "седан",
             "Altezza", "седан",
             "Aqua", "седан",
             "Aristo", "седан",
             "Aurion", "седан",
             "Auris", "седан",
             "Avalon", "седан",
             "Avanza", "седан",
             "Avensis", "седан",
             "Avensis Verso", "седан",
             "Aygo", "седан",
             "Aygo X", "седан",
             "bB", "седан",
             "Belta", "седан",
             "Blade", "седан",
             "Blizzard", "седан",
             "Brevis", "седан",
             "bZ4X", "седан",
             "C-HR", "седан",
             "Caldina", "седан",
             "Cami", "седан",
             "Camry", "седан",
             "Camry Solara", "седан",
             "Carina", "седан",
             "Carina E", "седан",
             "Carina ED", "седан",
             "Cavalier", "седан",
             "Celica", "седан",
             "Celsior", "седан",
             "Century", "седан",
             "Chaser", "седан",
             "Classic", "седан",
             "Comfort", "седан",
             "COMS", "седан",
             "Corolla", "седан",
             "Corolla Cross", "седан",
             "Corolla II", "седан",
             "Corolla Levin", "седан",
             "Corolla Rumion", "седан",
             "Corolla Spacio", "седан",
             "Corolla Verso", "седан",
             "Corona", "седан",
             "Corona EXiV", "седан",
             "Corsa", "седан",
             "Cressida", "седан",
             "Cresta", "седан",
             "Crown", "седан",
             "Crown Kluger", "седан",
             "Crown Majesta", "седан",
             "Curren", "седан",
             "Cynos", "седан",
             "Duet", "седан",
             "Echo", "седан",
             "Esquire", "седан",
             "Estima", "седан",
             "Etios", "седан",
             "FJ Cruiser", "седан",
             "Fortuner", "седан",
             "Frontlander", "седан",
             "FunCargo", "седан",
             "Gaia", "седан",
             "GR86", "седан",
             "Grand HiAce", "седан",
             "Granvia", "седан",
             "GT86", "седан",
             "Harrier", "седан",
             "HiAce", "седан",
             "Highlander", "седан",
             "Hilux", "седан",
             "Hilux Surf", "седан",
             "Innova", "седан",
             "Ipsum", "седан",
             "iQ", "седан",
             "ISis", "седан",
             "Ist", "седан",
             "Izoa", "седан",
             "Kluger", "седан",
             "Land Cruiser", "седан",
             "Land Cruiser Prado", "седан",
             "Levin", "седан",
             "Lite Ace", "седан",
             "Mark II", "седан",
             "Mark X", "седан",
             "Mark X ZiO", "седан",
             "MasterAce Surf", "седан",
             "Matrix", "седан",
             "Mega Cruiser", "седан",
             "Mirai", "седан",
             "Model F", "седан",
             "MR-S", "седан",
             "MR2", "седан",
             "Nadia", "седан",
             "Noah", "седан",
             "Opa", "седан",
             "Origin", "седан",
             "Paseo", "седан",
             "Passo", "седан",
             "Passo Sette", "седан",
             "Picnic", "седан",
             "Pixis Epoch", "седан",
             "Pixis Joy", "седан",
             "Pixis Mega", "седан",
             "Pixis Space", "седан",
             "Pixis Van", "седан",
             "Platz", "седан",
             "Porte", "седан",
             "Premio", "седан",
             "Previa", "седан",
             "Prius", "седан",
             "Prius Alpha", "седан",
             "Prius c", "седан",
             "Prius v (+)", "седан",
             "ProAce", "седан",
             "ProAce City", "седан",
             "Probox", "седан",
             "Progres", "седан",
             "Pronard", "седан",
             "Publica", "седан",
             "Ractis", "седан",
             "Raize", "седан",
             "Raum", "седан",
             "RAV4", "седан",
             "Regius", "седан",
             "RegiusAce", "седан",
             "Roomy", "седан",
             "Rush", "седан",
             "Sai", "седан",
             "Scepter", "седан",
             "Sequoia", "седан",
             "Sera", "седан",
             "Sienna", "седан",
             "Sienta", "седан",
             "Soarer", "седан",
             "Soluna", "седан",
             "Spade", "седан",
             "Sparky", "седан",
             "Sports 800", "седан",
             "Sprinter", "седан",
             "Sprinter Carib", "седан",
             "Sprinter Marino", "седан",
             "Sprinter Trueno", "седан",
             "Starlet", "седан",
             "Succeed", "седан",
             "Supra", "седан",
             "Tacoma", "седан",
             "Tank", "седан",
             "Tercel", "седан",
             "Touring HiAce", "седан",
             "Town Ace", "седан",
             "Tundra", "седан",
             "Urban Cruiser", "седан",
             "Vanguard", "седан",
             "Vellfire", "седан",
             "Venza", "седан",
             "Verossa", "седан",
             "Verso", "седан",
             "Verso-S", "седан",
             "Vios", "седан",
             "Vista", "седан",
             "Vitz", "седан",
             "Voltz", "седан",
             "Voxy", "седан",
             "Wigo", "седан",
             "Wildlander", "седан",
             "WiLL", "седан",
             "WiLL Cypha", "седан",
             "Windom", "седан",
             "Wish", "седан",
             "Yaris", "седан",
             "Yaris Cross", "седан",
             "Yaris Verso", "седан",
             "Zelas", "седан"
    );

    private final Map<Integer, CarModel> models = new ConcurrentHashMap<>();
    private final CarMarcMemRepository marcs;

    public CarModelMemRepository(CarMarcMemRepository marcs) {
        this.marcs = marcs;
        int counter = 1;
        counter = addAudi(counter);
        counter = addMersedes(counter);
        counter = addHyundai(counter);
        counter = addRenault(counter);
        counter = addBmw(counter);
        counter = addKia(counter);
        counter = addMitsubishi(counter);
        counter = addFord(counter);
        counter = addNissan(counter);
        counter = addSubaru(counter);
        counter = addOpel(counter);
        counter = addToyota(counter);
    }

    /**
     * Список  отсортированных по id.
     * @return список моделей авто марки.
     */
    @Override
    public List<CarModel> findAll() {
        return models.values().stream().toList();
    }

    /**
     * Найти модель авто марки по ID
     * @param id идентификотор модели авто марки
     * @return модель авто марки.
     */
    @Override
    public Optional<CarModel> findById(int id) {
        return Optional.ofNullable(models.getOrDefault(id, null));
    }

    /**
     * Список моделей отсортированных по id
     * для выбранной по идентфикатору авто марки.
     * @param id идентификотор авто марки
     * @return список моделей авто марки.
     */
    @Override
    public List<CarModel> findAllByMarcId(int id) {
        return models.values()
                .stream()
                .filter(model -> model.getMarcId() == id)
                .toList();
    }

    /**
     * Список моделей отсортированных по id
     * для выбранной по типу кузова авто.
     * @param id идентификотор типа кузова авто
     * @return список всех моделей всех авто марок
     * с выбранным типом кузова.
     */
    @Override
    public List<CarModel> findAllByBodyId(int id) {
        return models.values()
                .stream()
                .filter(model -> model.getBodyId() == id)
                .toList();
    }

    /**
     * Список моделей отсортированных по id
     * для выбранной по марке и типу кузова авто.
     * @param marcId идентификотор авто марки
     * @param bodyId идентификотор типа кузова авто
     * @return список всех моделей всех авто марок
     * с выбранной маркой и типом кузова.
     */
    @Override
    public List<CarModel> findAllByMarcIdAndBodyId(int marcId, int bodyId) {
        return models.values()
                .stream()
                .filter(model -> model.getBodyId() == bodyId && model.getMarcId() == marcId)
                .toList();
    }

    /**
     * Добавляет модели в список моделей
     * @param marcId - идентификатор марки
     * @param modelCounter - счётчик для идентификатора модели
     * @param modelList - список пар строк имя_модели + тип_кузова
     * @return новый счётчик для идентификатора модели
     */
    private int addModels(int marcId, int modelCounter, List<String> modelList) {
        for (int i = 0; i < modelList.size();) {
            CarModel model = new CarModel(
                    modelCounter++,
                    modelList.get(i++),
                    marcId,
                    BODY_NAMES.indexOf(modelList.get(i++))
            );
            models.putIfAbsent(model.getId(), model);
        }
        return modelCounter;
    }

    /**
     * Добавляет модели Audi в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addAudi(int modelCounter) {
        return addModels(marcs.findIdByName("Audi"), modelCounter, audiModels);
    }

    /**
     * Добавляет модели Mersedes в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addMersedes(int modelCounter) {
        return addModels(marcs.findIdByName("Mercedes-Benz"), modelCounter, mersedesModels);
    }

    /**
     * Добавляет модели Hyundai в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addHyundai(int modelCounter) {
        return addModels(marcs.findIdByName("Hyundai"), modelCounter, hyundaiModels);
    }
    
    /**
     * Добавляет модели Renault в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addRenault(int modelCounter) {
        return addModels(marcs.findIdByName("Renault"), modelCounter, renaultModels);
    }

    /**
     * Добавляет модели BMW в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addBmw(int modelCounter) {
        return addModels(marcs.findIdByName("BMW"), modelCounter, bmwModels);
    }

    /**
     * Добавляет модели Kia в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addKia(int modelCounter) {
        return addModels(marcs.findIdByName("Kia"), modelCounter, kiaModels);
    }

    /**
     * Добавляет модели Mitsubishi в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addMitsubishi(int modelCounter) {
        return addModels(marcs.findIdByName("Mitsubishi"), modelCounter, mitsubishiModels);
    }

    /**
     * Добавляет модели Ford в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addFord(int modelCounter) {
        return addModels(marcs.findIdByName("Ford"), modelCounter, fordModels);
    }

    /**
     * Добавляет модели Nissan в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addNissan(int modelCounter) {
        return addModels(marcs.findIdByName("Nissan"), modelCounter, nissanModels);
    }

    /**
     * Добавляет модели Nissan в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addSubaru(int modelCounter) {
        return addModels(marcs.findIdByName("Subaru"), modelCounter, subaruModels);
    }

    /**
     * Добавляет модели Opel в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addOpel(int modelCounter) {
        return addModels(marcs.findIdByName("Opel"), modelCounter, opelModels);
    }

    /**
     * Добавляет модели Toyota в хранилище моделей
     * @param modelCounter - счётчик ID
     * @return новый счётчик ID
     */
    private int addToyota(int modelCounter) {
        return addModels(marcs.findIdByName("Toyota"), modelCounter, toyotaModels);
    }

    /**
     * Создать скрипты для заполнения БД
     * в папке проекта 2 файла
     * "c:\\projects\\job4j_cars\\db\\014_dml_insert_car_marc.sql"
     * "c:\\projects\\job4j_cars\\db\\015_dml_insert_car_model.sql"
     */
    private void createSql() {
        try (FileWriter writer1 = new FileWriter(
                "c:\\projects\\job4j_cars\\db\\014_dml_insert_car_marc.sql", false)) {
            marcs.findAll().forEach(
                    marc -> {
                        String text = "insert into car_marc (name) values ('"
                                + marc.getName() + "');";
                        try {
                            writer1.write(text);
                            writer1.append('\n');
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            writer1.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try (FileWriter writer2 = new FileWriter(
                "c:\\projects\\job4j_cars\\db\\015_dml_insert_car_model.sql",
                false)) {
            models.values().forEach(
                    model -> {
                        String text = "insert into car_model (name, marc_id, body_id) values ('"
                                + model.getName() + "', (select id from car_marc where name = '"
                                + marcs.findById(model.getMarcId())
                                .orElse(new CarMarc()).getName()
                                + "'), (select id from car_body where name ='"
                                + BODY_NAMES.get(model.getBodyId()) + "'));";
                        try {
                            writer2.write(text);
                            writer2.append(System.lineSeparator());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            writer2.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
